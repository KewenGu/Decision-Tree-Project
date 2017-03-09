# author: Kewen Gu & Zhaochen Ding

from math import log

# Data structure used by the decision tree algorithm
class TreeNode:
    def __init__(self, col=-1, value=1, results=None, tbranch=None, fbranch=None):
        self.col = col
        self.value = value
        self.results = results
        self.tbranch = tbranch
        self.fbranch = fbranch

# Partition the data into two set,
def partition(rows, column, value):
    # Define a split function using lambda expression
    split_function = lambda row: row[column] == value

    # divide the given data set into two subsets
    set1 = [row for row in rows if split_function(row)]
    set2 = [row for row in rows if not split_function(row)]

    return (set1, set2)

# Count the number of a particular value in the set
def valueCount(rows, col):
    results = {}
    for row in rows:
        r = row[col]
        if r not in results: results[r] = 0
        results[r] += 1
    return results

# Calculate the entropy of a given category, by using the formular : B(q) = -[q*log2(q) + (1-q)*log2(1-q)]
def calculateEntropy(rows, col):
    log2 = lambda x: log(x) / log(2)
    results = valueCount(rows, col)
    entropy = 0.0
    for r in results.keys():
        p = float(results[r]) / len(rows)
        entropy -= p * log2(p)

    return entropy

# Build the decision tree upon the information gain obtianed from the given data set
def buildDecisionTree(rows, rcol):
    if len(rows) == 0: return TreeNode()
    current_entropy = calculateEntropy(rows, rcol)

    best_gain = 0.0
    best_criteria = None
    best_sets = None

    column_count = len(rows[0])
    for col in xrange(column_count):
        if col != rcol:
            column_values = {}
            for row in rows:
                column_values[row[col]] = 1

            for value in column_values.keys():
                (set1, set2) = partition(rows, col, value)
                # Calculate information gain
                p = float(len(set1)) / len(rows)
                gain = current_entropy - p * calculateEntropy(set1, rcol) - (1 - p) * calculateEntropy(set2, rcol)
                if gain > best_gain and len(set1) > 0 and len(set2) > 0:
                    best_gain = gain
                    best_criteria = (col, value)
                    best_sets = (set1, set2)
    if best_gain > 0:
        trueBranch = buildDecisionTree(best_sets[0], rcol)
        falseBranch = buildDecisionTree(best_sets[1], rcol)
        return TreeNode(col=best_criteria[0], value=best_criteria[1], tbranch=trueBranch, fbranch=falseBranch)
    else:
        return TreeNode(results=valueCount(rows, rcol))

# Perform classification for a given observation
def classification(observation, tree):
    if tree.results != None:
        return tree.results
    else:
        v = observation[tree.col]
        branch = None
        if isinstance(v, int) or isinstance(v, float):
            if v >= tree.value:
                branch = tree.tbranch
            else:
                branch = tree.fbranch
        else:
            if v == tree.value:
                branch = tree.tbranch
            else:
                branch = tree.fbranch
        return classification(observation, branch)

# Learning from the decision tree and gather classification results
def decisionTreeLearner(trainset, testset, rcol):
    model = buildDecisionTree(trainset, rcol)
    correct = 0
    incorrect = 0
    for row in testset:
        guess = classification(row, model)
        # print guess
        if not guess.has_key(row[rcol]):
            incorrect += 1
        else:
            correct += 1
    return correct, incorrect

# Perform k-fold cross validation
def crossValidateion(dataSet, rcol=5, kfold=10):
    error = 0.0

    # Partition the data set into k-folds
    length = len(dataSet)
    folds = []
    for x in xrange(kfold):
        size = length / kfold
        folds.append(dataSet[x * size: (x + 1) * size])
        if x == kfold - 1:
            folds.append(dataSet[x * size:])

    # Do cross validation and build the decision tree
    correctCnt = 0
    incorrectCnt = 0
    for y in xrange(kfold):
        testSet = folds[y]
        tempSet = folds[:y] + folds[y + 1:]
        trainSet = []
        for set in tempSet:
            trainSet += set

        correct, incorrect = decisionTreeLearner(trainSet, testSet, rcol)
        correctCnt += correct
        incorrectCnt += incorrect

    # Calculate overall correct count, incorrect count, and error rate
    correctCnt = correctCnt / kfold
    incorrectCnt = incorrectCnt / kfold
    error += float(incorrectCnt) / float(correctCnt + incorrectCnt)
    return correctCnt, incorrectCnt, error

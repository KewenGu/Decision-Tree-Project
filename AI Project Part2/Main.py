
import sys
import DecisionTree as dt

def main():

    # Read the fold number from the command line
    kfold = 0
    if len(sys.argv) <= 2:
        kfold = int(sys.argv[1])
    else :
        print "Invalid argument count: %d" % len(sys.argv)

    # Read the data set from file, the file should located in the same folder as the python file
    inFile = open("outputsFromPart1.csv")
    lines = inFile.readlines()
    dataSet = []
    del lines[0]

    for l in lines:
        dataSet.append(l.rstrip('\n').split(","))

    # Obtain the cross-validation result for every feature
    # Print the results to the console
    for i in xrange(5):
        correct, incorrect, error = dt.crossValidateion(dataSet, i, kfold)
        print "********************************************"
        print "Feature %d" % (i + 1)
        print "# of correctly classified instances: %d" % correct
        print "# of incorrectly classified instances: %d" % incorrect
        print "Error rate: %.4f" % error

if __name__ == "__main__":
    main()
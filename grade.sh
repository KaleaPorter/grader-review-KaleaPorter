CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'
# making a variable - do not have any spaces otherwise bash thinks it's a command

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'

pwd
if ! [ -f student-submission/ListExamples.java ]
then 
    echo "Missing Necessary Files"
    exit
fi 
echo "continue"

cp TestListExamples.java student-submission/ListExamples.java grading-area
cp -r lib grading-area #recursive copy of lib directory

cd grading-area
pwd

javac -cp $CPATH *.java
if [ $? -ne 0 ]
then 
    echo "Compilation Error"
    exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > junit-output.txt
if [[ `grep "FAILURES!!!" junit-output.txt` ]]
then 
    lastline=$(cat junit-output.txt | tail -n 2 | head -n 1)
    tests=$(echo $lastline | awk -F'[, ]' '{print $3}') #awk is a separator
    failures=$(echo $lastline | awk -F'[, ]' '{print $6}')
    successes=$((tests - failures))
    echo "Your score is $successes / $tests"
elif [[ `grep "OK" junit-output.txt` ]]
then
    lastlineOK=$(cat junit-output.txt | tail -n 2 | head -n 1)
    testNumber=$(echo $lastline | awk -F'[(]' '{print $4}')
    echo "Your score is 100%"
fi


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests

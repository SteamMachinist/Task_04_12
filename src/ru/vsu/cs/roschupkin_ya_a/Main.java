package ru.vsu.cs.roschupkin_ya_a;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ROOT);

        ArrayList<Integer> sequenceTerms = new ArrayList<>();

        int numberOfSummarizedPreviousTerms
                = readParameter ("number K of summarized previous terms of sequence for a new term K");

        int[] initialPreviousTerms = createInitialPreviousTerms(
                numberOfSummarizedPreviousTerms, sequenceTerms);

        int bottomBorder = readParameter("bottom border for sequence term");

        calculateSequenceTermsUntilBiggerThanBottomBorder(bottomBorder, sequenceTerms, initialPreviousTerms);

        printSequence(sequenceTerms, bottomBorder);
    }

    static void printSequence(ArrayList<Integer> sequenceTerms, int bottomBorder)
    {
        System.out.println();

        for (Integer sequenceTerm : sequenceTerms)
        {
            System.out.printf("%d ", sequenceTerm);
        }

        System.out.printf("%n%nThe first sequence term bigger or equal to %d is %d",
                bottomBorder, sequenceTerms.get(sequenceTerms.size() - 1));
    }


    static int[] createInitialPreviousTerms(int numberOfSummarizedPreviousTerms, ArrayList<Integer> sequenceTerms)
    {
        int[] previousTerms = new int[numberOfSummarizedPreviousTerms];

        for (int previousTermNumber = 0;
             previousTermNumber < (previousTerms.length - 1); previousTermNumber++)
        {
            previousTerms[previousTermNumber] = 0;
            sequenceTerms.add(0);
        }

        previousTerms[previousTerms.length - 1] = 1;
        sequenceTerms.add(1);

        return previousTerms;
    }

    static void calculateSequenceTermsUntilBiggerThanBottomBorder(
            int bottomBorder, ArrayList<Integer> sequenceTerms, int[] previousTerms)
    {
        int newTerm = 0;

        while (newTerm < bottomBorder)
        {
            newTerm = calculateNewTerm(previousTerms);
            sequenceTerms.add(newTerm);

            createNewPreviousTerms(previousTerms, newTerm);
        }
    }

    static int calculateNewTerm(int[] previousTerms)
    {
        int newTerm = 0;
        for (int previousTerm : previousTerms)
        {
            newTerm += previousTerm;
        }
        return newTerm;
    }

    static void createNewPreviousTerms(int[] previousTerms, int newTerm)
    {
        if (previousTerms.length - 1 >= 0)
            System.arraycopy(previousTerms, 1, previousTerms, 0, previousTerms.length - 1);

        previousTerms[previousTerms.length - 1] = newTerm;
    }

    static int readParameter(String parameterName)
    {
        System.out.printf("%nInput %s: ", parameterName);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}

package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * You're developing a system for scheduling advising meetings with students in a Computer Science program. Each meeting should be scheduled when a student has completed 50% of their academic program.
 * <p>
 * Each course at our university has at most one prerequisite that must be taken first. No two courses share a prerequisite. There is only one path through the program.
 * <p>
 * Write a function that takes a list of (prerequisite, course) pairs, and returns the name of the course that the student will be taking when they are halfway through their program. (If a track has an even number of courses, and therefore has two "middle" courses, you should return the first one.)
 * <p>
 * Sample input 1: (arbitrarily ordered)
 * pairs1 = [
 * ["Foundations of Computer Science", "Operating Systems"],
 * ["Data Structures", "Algorithms"],
 * ["Computer Networks", "Computer Architecture"],
 * ["Algorithms", "Foundations of Computer Science"],
 * ["Computer Architecture", "Data Structures"],
 * ["Software Design", "Computer Networks"]
 * ]
 * <p>
 * In this case, the order of the courses in the program is:
 * Software Design
 * Computer Networks
 * Computer Architecture
 * Data Structures
 * Algorithms
 * Foundations of Computer Science
 * Operating Systems
 * <p>
 * Sample output 1:
 * "Data Structures"
 * <p>
 * Sample input 2:
 * pairs2 = [
 * ["Algorithms", "Foundations of Computer Science"],
 * ["Data Structures", "Algorithms"],
 * ["Foundations of Computer Science", "Logic"],
 * ["Logic", "Compilers"],
 * ["Compilers", "Distributed Systems"],
 * ]
 * <p>
 * Sample output 2:
 * "Foundations of Computer Science"
 * <p>
 * Sample input 3:
 * pairs3 = [
 * ["Data Structures", "Algorithms"],
 * ]
 * <p>
 * Sample output 3:
 * "Data Structures"
 * <p>
 * All Test Cases:
 * halfway_course(pairs1) => "Data Structures"
 * halfway_course(pairs2) => "Foundations of Computer Science"
 * halfway_course(pairs3) => "Data Structures"
 * <p>
 * Complexity analysis variables:
 * <p>
 * n: number of pairs in the input
 **/
public class FindHalfWayCourse {
    static HashSet<Integer> traverseRows = new HashSet<>();

    public static void main(String[] args) {
        String[][] pairs1 = {
                {"Foundations of Computer Science", "Operating Systems"},
                {"Data Structures", "Algorithms"},
                {"Computer Networks", "Computer Architecture"},
                {"Algorithms", "Foundations of Computer Science"},
                {"Computer Architecture", "Data Structures"},
                {"Software Design", "Computer Networks"}
        };

        String[][] pairs2 = {
                {"Algorithms", "Foundations of Computer Science"},
                {"Data Structures", "Algorithms"},
                {"Foundations of Computer Science", "Logic"},
                {"Logic", "Compilers"},
                {"Compilers", "Distributed Systems"},
        };

        String[][] pairs3 = {
                {"Data Structures", "Algorithms"}
        };

        ///////////////////////////////////////////////

        String result = halfway_course(pairs1);
        System.out.println("pairs1---> " + result);

        result = halfway_course(pairs2);
        System.out.println("pairs2---> " + result);

        result = halfway_course(pairs3);
        System.out.println("pairs3---> " + result);
    }

    public static String halfway_course(String[][] pairs) {
        List<String> coursePathAll = new ArrayList<>();

        findAllParentCourse(pairs, pairs[0][0], coursePathAll);
        traverseRows.clear();
        findAllChildCourse(pairs, pairs[0][1], coursePathAll);

        int midIdx = (coursePathAll.size() - 1) / 2;
        return coursePathAll.get(midIdx);
    }

    /**
     * From this childCourse, traverse to its parent and then from that parent to its parent...
     * Add each course into the coursePath list
     *
     * @param pairs
     * @param childCourse
     * @param coursePath
     */
    public static void findAllParentCourse(String[][] pairs, String childCourse, List<String> coursePath) {
        for (int i = 0; i < pairs.length; i++) {
            // Skip already traversed rows
            if (traverseRows.contains(i))
                continue;

            if (pairs[i][1].equals(childCourse)) {
                traverseRows.add(i);

                findAllParentCourse(pairs, pairs[i][0], coursePath);

                coursePath.add(childCourse);
                return;
            }
        }

        // This is to add the root course into the list first
        coursePath.add(childCourse);
    }

    /**
     * From this parentCourse, traverse to its child and then from that child to its child...
     * Add each course into the coursePath list
     *
     * @param pairs
     * @param parentCourse
     * @param coursePath
     */
    public static void findAllChildCourse(String[][] pairs, String parentCourse, List<String> coursePath) {
        for (int i = 0; i < pairs.length; i++) {
            // Skip already traversed rows
            if (traverseRows.contains(i))
                continue;

            if (pairs[i][0].equals(parentCourse)) {
                traverseRows.add(i);

                coursePath.add(parentCourse);

                findAllChildCourse(pairs, pairs[i][1], coursePath);
                return;
            }
        }

        // This is to add the bottom course into the list first
        coursePath.add(parentCourse);
    }
}

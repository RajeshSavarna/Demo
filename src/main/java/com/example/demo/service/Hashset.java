/*
1. HashSet in Java
A football team has played n matches in a tournament.
They have played each match with different sets of players (some players may have continuously played in some of the matches).
You have to write a program to find the players who have played all the matches and the players who have played in a particular match
and not in the other mentioned match.

Input Specifications:

The first line consists of an integer n denoting the number of matches.
The second line consists of a single string containing the squad of players in each match, separated by a #.
The players in a particular match are separated by a single space (the players for a single match may contain duplicates as well,
you need to remove the duplicates).
The last two lines consist of two integers x and y,
which denotes the match numbers depending on which you have to find the players who have played in match y, but not in x.
 
Output Specifications:

Output a string with the players who have played all the matches with the names separated by a single space,
followed by a comma and space, and the players who have played match number y 
and have not played match number x with the names separated by a single space.

Sample Input:
4
Ronaldo Eder Andre Bruma Goncalo Jota Daniel Patrício Ricardo Domingos Semedo Bernardo Pereira Bruno Pizzi Paciencia Jota Daniel Patrício Eder Andre Bruma Goncalo Jota Daniel Patrício Ricardo Domingos Semedo Bernardo Pereira Bruno
#Ronaldo Eder Andre Bruma Goncalo Joao Dyego Jose Nelson Pepe Mario Pereira Rafa Renato Ferro Bruma Goncalo Joao Dyego Pereira Rafa Renato Ferro Bruma Goncalo Joao Dyego
#Ronaldo Eder Andre Bruma Goncalo Jota Daniel Patrício Nelson Pepe Mario Pereira Rafa Renato Ferro Daniel Patrício Daniel Patrício Nelson Pepe Mario Pereira Nelson Pepe Mario Pereira Rafa Renato Ferro Daniel Patrício
#Ronaldo Eder Andre Bruma Goncalo Joao Dyego Jose Ricardo Domingos Semedo Bernardo Pereira Bruno Pizzi Andre Bruma Goncalo Joao Dyego Jose Ricardo Domingos Semedo Bernardo
2
4

Sample Output:
Pereira Ronaldo Goncalo Eder Andre Bruma, Ricardo Pizzi Semedo Bruno Domingos Bernardo

Explanation:
Players Pereira, Ronaldo, Goncalo, Eder, Andre, and Bruma have played in all 4 matches.
Players Ricardo, Pizzi, Semedo, Bruno, Domingos and Bernardo have played in the 4th match but not in the 2nd.

Note:

You have to use HashSet for this program.
Since you are using HashSet, the order of the names of the players in the output can be in any order.
 */

package com.example.demo.service;
import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class Hashset
{
    public static String getOut(int numberOfMatches, String squads, int squad1, int squad2)
    {
    	String res = "";

        ArrayList<Set<String>> list = new ArrayList<>(); 
        for (String players: squads.split("#")) {
        	String[] values = players.split(" ");
        	Set<String> hashSet = new HashSet<String>(Arrays.asList(values));
        	list.add(hashSet);
        }
        Set<String> allPlayers = new HashSet<String>(Arrays.asList(squads.replaceAll("#", "").split(" ")));
        for (Set<String> matchPlayer : list) {
        	allPlayers.retainAll(matchPlayer);
        }
        for(String player : allPlayers) res += player+" ";
        res = res.trim() + ", ";
        for (String player: list.get(squad2 -  1)) {
        	if (!(list.get(squad1 -  1).contains(player))) {
        		res += player+" ";
        	}
        }
        return res.trim();
    }
}

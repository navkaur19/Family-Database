import java.util.*;


import java.io.*;

public class FamilyMain {
    public static void main (String[] args) throws FileNotFoundException {
        giveIntro();
        Scanner console = new Scanner(System.in);
        Scanner input = new Scanner(new File("tudor.txt"));
        FamilyDatabase family = new FamilyDatabase(input);
        family.print();
        doMatches(family, console);
    }

    public static void giveIntro() {
        System.out.println("This program reads an input file with family");
        System.out.println("information and provides information about the");
        System.out.println("maternal line, paternal line and children of");
        System.out.println("various people.");
        System.out.println();
    }

    public static void doMatches(FamilyDatabase family, Scanner console) {
        System.out.print("next person (enter to quit)? ");
        String name = console.nextLine();
        while (name.length() > 0) {
            Person next = family.find(name);
            if (next == null) {
                System.out.println("No match.");
            } else {
                showMaternal(next);
                showPaternal(next);
                showChildren(next);
                showSiblings(next);
            }
            System.out.println();
            System.out.print("next person (enter to quit)? ");
            name = console.nextLine();
        }
    }

    /* Shows maternal ancestors for given person */
    public static void showMaternal(Person current) {
        System.out.println("Maternal line:");
        int level = 1;
        while (current != null) {
            for (int i = 0; i < level; i++)
                System.out.print("    ");
            	System.out.println(current.getName());
            current = current.getMother();
            level++;
        }
        System.out.println("There are "+ (level-1) + " records");
        
    }

    /* Shows paternal ancestors for given person */
    public static void showPaternal(Person current) {
        System.out.println("Paternal line:");
        int level = 1;
        while (current != null) {
            for (int i = 0; i < level; i++)
                System.out.print("    ");
            	System.out.println(current.getName());
            current = current.getFather();
            level++;
        }
        
        System.out.println(" There are "+ (level-1) + " records");
    }

    /* Shows children for given person */
    public static void showChildren(Person current) {
        ArrayList<Person> kids = current.getChildren(current);
        System.out.println("Children:");
        if (kids.size() == 0) {
            System.out.println("    none");
        } else {
            for (Person kid: kids) {
            	System.out.println("    " + kid.getName());
                }
            if (kids.size()== 1) {
            	System.out.println(" There is only one child ");
            	}
            else {
            System.out.println(" There are "+kids.size() + " kids ");
            }
        }
    }
    
    
    public static void showSiblings(Person current) {
    	ArrayList<Person> array1 = new ArrayList<>();
    	ArrayList<Person> array2 = new ArrayList<>();
    	ArrayList<Person> array3 = new ArrayList<>();
    	ArrayList<Person> array4 = new ArrayList<>();
    	
    	System.out.println("Siblings: ");
    	// Father's children
    	if(current.getFather() == null) {
           	System.out.println("No paternal record ");
           }
    	 else {
    	array1.addAll(current.getFather().getChildren(current));
    	 }
    	 
    	for (Person person : array1) {
            if (!person.getName().equals(current.getName())) {
                array2.add(person);
              }
    	}
    	// Mother's children
    	if(current.getMother()== null) {
 			System.out.println("No maternal record ");
 		}
    	else {
    	array3.addAll(current.getMother().getChildren(current));
    	}
    	for(Person person : array3) {
     		if (!person.getName().equals(current.getName())) {
     			array4.add(person);
     			}
     		}
     	// Compare both array lists 
    	array4.retainAll(array2);
     	for (Person human : array4) {
     		System.out.println("  " + human.getName());
     	}
     		if(array4.size() == 0) {
     		System.out.println("There are no siblings ");
     	}
     		
     	
    	}	
      }


    
    

    	
    	
    
    	
    	
    	
    
    
  



package dk.kea.dat3js.hogwarts5.generic;

import java.util.Arrays;

public interface PersonWithNames {
    String getFirstName();
    String getMiddleName();
    String getLastName();
    void setFirstName(String firstName);
    void setMiddleName(String middleName);
    void setLastName(String lastName);

    default String getFullName() {
        if(getLastName() == null){
            return getFirstName();
        }
        if(getMiddleName() == null){
            return getFirstName() + " " + getLastName();
        }
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

    default void setFullName(String fullName){
        if(fullName == null){
            return;
        }

        int firstSpace = fullName.indexOf(" ");
        int lastSpace = fullName.lastIndexOf(" ");

        if(firstSpace == -1){
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
        }
        else if(firstSpace == lastSpace){
            setFirstName(fullName.substring(0, firstSpace));
            setMiddleName(null);
            setLastName(fullName.substring(lastSpace+1));
        }
        else {
            setFirstName(fullName.substring(0, firstSpace));
            setMiddleName(fullName.substring(firstSpace+1, lastSpace));
            setLastName(fullName.substring(lastSpace+1));
        }
    }

    default String capitalize(String s){
        if(s == null){
            return null;
        }
        if(s.isEmpty()){
            return "";
        }
        if(s.length() == 1){
            return s.toUpperCase();
        }

        s = s.trim();
        if(s.contains(" ")){
            var parts = s.split(" ");
            var capitalizedList = Arrays.stream(parts).map(this::capitalize).toList();
            return String.join(" ", capitalizedList);
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

}

public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Gets the name of the person.
    public String getName() {
        return name;
    }

    //Gets the surname of the person.
    public String getSurname() {
        return surname;
    }

    //Gets the email of the person.
    public String getEmail() {
        return email;
    }


    //Prints the name, surname, and email of the person.
    private void print_person_info() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}


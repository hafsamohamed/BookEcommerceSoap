package gov.iti.jets;


import gov.iti.jets.services.UserService;
import gov.iti.jets.services.impl.UserServiceImpl;

/**
 * Hello world!
 *
 */

public class App 
{
    public static UserService userService = new UserServiceImpl();

    public static void main( String[] args )
    {
        //final EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory("myapp");

        //EntityManager entityManager = entityManagerFactory.createEntityManager();
    }

}

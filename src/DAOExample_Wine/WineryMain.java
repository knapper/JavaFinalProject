package DAOExample_Wine;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;;
public class WineryMain {
    public static void main(String[] args) {
        WineryDAO wineryDao = new WineryDAOImpl();
        // get one winery record
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Type the winery ID: ");
            int idToFind = input.nextInt();

            Winery oneWinery = null;
            oneWinery = wineryDao.getOne(idToFind);

            if (oneWinery != null){
                System.out.println(oneWinery.getWineryName() + " " + oneWinery.getViticulturalAreaID() + " " + oneWinery.getOfferingToursFlag());
            }else{
                System.out.println("Winery not found");
            }
        }catch (Exception e){
            System.out.println("Something went wrong: " + e);
        }

        // get all winery records
        try {
            List<Winery> allWineries = new ArrayList<>();
            allWineries = wineryDao.getAll();
            for (Winery oneWinery : allWineries){
                System.out.println(oneWinery.getWineryName() + " " + oneWinery.getViticulturalAreaID() + " " + oneWinery.getOfferingToursFlag());
            }  
        }catch (Exception e){
            System.out.println("Something went wrong: " + e);
        }
    }
    
}

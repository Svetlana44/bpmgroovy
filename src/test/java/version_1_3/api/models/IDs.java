package version_1_3.api.models;

import com.github.javaparser.utils.Log;
import responsparser.IDparser;
import utilies.Auth;
import utilies.frame.AccountServiceFrame;
import utilies.frame.ContactTypeServiceFrame;
import utilies.frame.CountryServiceFrame;

import java.util.List;
import java.util.Random;

import static utilies.frame.ContactServiciesFrame.getAllIdOfContacts;

public class IDs {
    private final Random random = new Random();
    public List<String> ownerIDs;
    public int randomNumberIdOwner;
    public List<String> countryIDs;
    public List<String> accountIDs;
    public int randomNumberIdCountry;
    public List<String> contactTypeIDs;
    public int randomNumberIdContactType;
    public int randomNumberIdAccount;
    public Auth auth;
    public String typeUrl;

    public IDs(Auth auth, String typeUrl) {
        this.auth = auth;
        this.typeUrl = typeUrl;

        ownerIDs = IDparser.parsIdFromIdResponseToList(getAllIdOfContacts(auth, typeUrl));
        Log.info("Спарсили ownerIDs===========");
        //System.out.println("System.out.println: OwnerIds=========" + ownerIDs.get(randomNumberIdOwner));
        randomNumberIdOwner = getRandomNumberId(ownerIDs);

        countryIDs = IDparser.parsIdFromIdResponseToList(CountryServiceFrame.getAllIdOfCountriesFrame(auth));
        Log.info("Спарсили countryIDs===========");
        randomNumberIdCountry = getRandomNumberId(countryIDs);
        //  System.out.println("System.out.println: countryIDs=========" + countryIDs.get(randomNumberIdCountry));

        contactTypeIDs = IDparser.parsIdFromIdResponseToList(ContactTypeServiceFrame.getAllIdOfContactTypeFrame(auth));
        Log.info("Спарсили contactTypeIDs (сейчас 4)===========");
        randomNumberIdContactType = getRandomNumberId(contactTypeIDs);
//        System.out.println("System.out.println: contactTypeIDs=========" + contactTypeIDs.get(randomNumberIdContactType));

        accountIDs = IDparser.parsIdFromIdResponseToList(AccountServiceFrame.getAllIdOfAccountsFrame(auth));
        Log.info("Спарсили accountIDs===========");
        randomNumberIdAccount = getRandomNumberId(accountIDs);
    }

    private int getRandomNumberId(List<String> IDsList) {
        if (IDsList.size() > 1) {
            return Math.abs(random.nextInt(IDsList.size() - 1));
        }
        return 0;
    }
}

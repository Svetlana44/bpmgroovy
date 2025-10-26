package api.models;

import com.github.javaparser.utils.Log;
import responsparser.IDparser;
import utilies.*;

import java.util.List;
import java.util.Random;

import static utilies.ContactServicies.getAllIdOfContacts;

/* нужно переделать и на фрэйм и на кор по типу  */
public class IDs {
    private final Random random = new Random();
    public List<String> ownerIDs;
    public List<String> countryIDs;
    public List<String> accountIDs;
    public List<String> contactIDs;
    public List<String> contactTypeIDs;
    public List<String> accountTypeIDs;
    public List<String> leadTypeIDs;

    public Auth auth;
    public String typeUrl;

    public IDs(Auth auth, String typeUrl) {
        this.auth = auth;
        this.typeUrl = typeUrl;
        this.auth.typeUrl = typeUrl;

        ownerIDs = IDparser.parsIdFromIdResponseToList(getAllIdOfContacts(auth, typeUrl));
        Log.info("Спарсили ownerIDs===========");
        //System.out.println("System.out.println: OwnerIds=========" + ownerIDs.get(randomNumberIdOwner));

        countryIDs = IDparser.parsIdFromIdResponseToList(CountryService.getAllIdOfCountries(auth, typeUrl));
        Log.info("Спарсили countryIDs===========");
        //  System.out.println("System.out.println: countryIDs=========" + countryIDs.get(randomNumberIdCountry));

        contactIDs = IDparser.parsIdFromIdResponseToList(ContactServicies.getAllIdOfContacts(auth, typeUrl));
        Log.info("Спарсили accountIDs===========");

        contactTypeIDs = IDparser.parsIdFromIdResponseToList(ContactTypeService.getAllIdOfContactTypeFrame(auth, typeUrl));
        Log.info("Спарсили contactTypeIDs (сейчас 4)===========");
//        System.out.println("System.out.println: contactTypeIDs=========" + contactTypeIDs.get(randomNumberIdContactType));

        accountIDs = IDparser.parsIdFromIdResponseToList(AccountService.getAllIdOfAccountsFrame(auth, typeUrl));
        Log.info("Спарсили accountIDs===========");

        accountTypeIDs = IDparser.parsIdFromIdResponseToList(AccountService.getAllIdOfAccountsTypeFrame(auth, typeUrl));
        Log.info("Спарсили accountTypeIDs===========");

        leadTypeIDs = IDparser.parsIdFromIdResponseToList(LeadService.getAllIdOfLeadType(auth, typeUrl));
        Log.info("Спарсили leadTypeIDs===========");
    }

    public int getRandomNumberId(List<String> IDsList) {
        if (IDsList.size() > 1) {
            return Math.abs(random.nextInt(IDsList.size() - 1));
        }
        return 0;
    }
}

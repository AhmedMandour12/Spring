

import java.util.ArrayList;
import java.util.List;

public class EWalletServiceImpl implements EWalletService {

    private List<Account> accounts = new ArrayList<>();

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }
}

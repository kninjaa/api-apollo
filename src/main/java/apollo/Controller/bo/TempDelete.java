package apollo.Controller.bo;

import apollo.Model.beans.Account;
import java.util.*;

public class TempDelete {
    public boolean isInactiveAfterOneYear(Account account) {
        Date currentDate = new Date();
        Date startDate = account.getDateStart();
        Date endDate = account.getDateEnd();

        if (startDate != null && endDate != null) {
            long differenceInMillis = endDate.getTime() - startDate.getTime();
            long differenceInYears = differenceInMillis / (1000L * 60L * 60L * 24L * 365L);

            return differenceInYears >= 1;
        }
        return false;
    }
}

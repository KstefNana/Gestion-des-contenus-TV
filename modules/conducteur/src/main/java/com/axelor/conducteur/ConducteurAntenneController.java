package com.axelor.conducteur;

import com.axelor.conducteur.db.ConducteurAntenne;
import com.axelor.conducteur.db.EtatValidation;
import com.axelor.db.JpaSupport;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

import java.time.LocalDate;

public class ConducteurAntenneController extends JpaSupport {

    public void onConfirm(ActionRequest request, ActionResponse response){
        ConducteurAntenne conducteurAntenne = request.getContext().asType(ConducteurAntenne.class);

        response.setReadonly("dateEnregistrement", conducteurAntenne.getConfirmed());

        if (conducteurAntenne.getConfirmed() == Boolean.TRUE && conducteurAntenne.getDateEnregistrement() == null) {
            response.setValue("dateEnregistrement", LocalDate.now());
        }

        if (conducteurAntenne.getConfirmed() == Boolean.TRUE) {
            response.setValue("status", EtatValidation.ETUDE);
        } else if (conducteurAntenne.getEtat() == EtatValidation.ETUDE) {
            response.setValue("status", EtatValidation.BROUILLON);
        }
    }
}




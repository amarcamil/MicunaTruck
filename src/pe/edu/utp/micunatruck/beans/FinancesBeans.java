package pe.edu.utp.micunatruck.beans;

import pe.edu.utp.micunatruck.models.Finance;
import pe.edu.utp.micunatruck.services.MicunaTruckService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class FinancesBeans implements Serializable {
    private MicunaTruckService micunaTruckService;
    private Finance finance;

    public FinancesBeans() {
        micunaTruckService = new MicunaTruckService();
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public int getOriginId() {
        return this.getFinance().getOriginId();
    }

    public void setOriginId(int originId) {
        this.getFinance().setOriginId(originId);
    }

    public int getOriginTypeId() {
        return this.getFinance().getOriginTypeId();
    }

    public void setOriginTypeId(int originTypeId) {
        this.getFinance().setOriginTypeId(originTypeId);
    }

    public double getAmount() {
        return this.getFinance().getAmount();
    }

    public void setAmount(double amount) {
        this.getFinance().setAmount(amount);
    }

    public Date getStartDate() {
        return this.getFinance().getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.getFinance().setStartDate(startDate);
    }

    public Date getEndDate() {
        return this.getFinance().getEndDate();
    }

    public void setEndDate(Date endDate) {
        this.getFinance().setEndDate(endDate);
    }

    public boolean getFlagActive() {
        return this.getFinance().getFlagActive();
    }

    public void setFlagActive(boolean flagActive) {
        this.getFinance().setFlagActive(flagActive);
    }
}
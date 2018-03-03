package pe.edu.utp.micunatruck.beans;

import pe.edu.utp.micunatruck.models.Admin;
import pe.edu.utp.micunatruck.models.Ads;
import pe.edu.utp.micunatruck.services.MicunaTruckService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class AdsBean implements Serializable {
    private MicunaTruckService micunaTruckService;
    private Ads ads;

    public AdsBean() {
        micunaTruckService=new MicunaTruckService();
    }

    public List<Ads> getAdsRs(){
        return micunaTruckService.findAllAds();
    }

    public Ads getAds(){
        return ads;
    }

    public void setAds(Ads ads){
        this.ads=ads;
    }

    public int getId(){
        return this.getAds().getId();
    }

    public Admin getAdmin(){
        return this.getAds().getAdmin();
    }

    public void setAdmin(){
    }

    public String getName(){
        return this.getAds().getName();
    }

    public String getDescription(){
        return this.getAds().getDescription();
    }

    public String getImage(){
        return this.getAds().getImage();
    }

    public Double getPrice(){
        return this.getAds().getPrice();
    }

    public Date getStartDate(){
        return this.getAds().getStart_date();
    }

    public Date getEndDate(){
        return  this.getAds().getEnd_date();
    }

    public int getFlagActive(){
        return this.getAds().getFlagActive();
    }

    public Date getCreatedAt(){
        return this.getAds().getCreatedAt();
    }

    public Date getUpdatedAt(){
        return this.getAds().getUpdatedAt();
    }

    public int getcodespace(){
        return this.getAds().getCodeSpace();
    }

    public String editAds(Ads ads){
        this.setAds(ads);
        return "success";
    }

    public String newAds(){
        this.setAds(new Ads());
        return "success";
    }

    public String createAds(){
        micunaTruckService.createAds(getAdmin().getId(),getName(),
                getDescription(),getImage(),getPrice(),getStartDate(),getEndDate(),getcodespace());
        return "success";
    }

    public String updateAds(){
        micunaTruckService.updateAds(getId(),getName(),getDescription(),getImage(),getPrice(),
                getStartDate(),getEndDate(),getFlagActive(),getcodespace());
        return "success";
    }

    public String deleteAds(Ads ads){
        try {
            micunaTruckService.deleteAds(ads.getId());
            return "success";
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
}
package ua.org.ecity.entities;

public class AdminPanelResult {

    private AdminPanelStatus adminPanelStatus;
    private int cityId;

    public AdminPanelResult(AdminPanelStatus adminPanelStatus, int cityId) {
        this.adminPanelStatus = adminPanelStatus;
        this.cityId = cityId;
    }

    public AdminPanelStatus getAdminPanelStatus() {
        return adminPanelStatus;
    }

    public void setAdminPanelStatus(AdminPanelStatus adminPanelStatus) {
        this.adminPanelStatus = adminPanelStatus;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "AdminPanelResult{" +
                "adminPanelStatus=" + adminPanelStatus +
                ", cityId=" + cityId +
                '}';
    }
}

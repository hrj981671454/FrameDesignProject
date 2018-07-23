package kotlin.studio.com.myapplication.bean;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2017/5/3 11:47
 */

public class LoginBean {


    /**
     * data : {"DRIVER_ID":"","FID":"ce66ebeb-f3b3-44f4-a614-fbad18af11da","IS_MODIFY_PWD":"Y","MASTER_ID":"bf891863-4c0c-46cb-b616-e3a16ebb7566","NAME":"朱海征（测试）","OMS_FID":"3996950","OMS_MASTER_ID":"YAJ20160707A901013","PHONE":"15110272604","PLAT_FROM_ID":"TJ001","PLAT_FROM_NAME":"天津网点","USER_TYPE":"1","authenticateCode":"","businessUnitCode":" ","businessUnitName":" ","cusGroup":"","customer_code":"","customer_name":"","enableCode":"","isReviewFlag":"","nickname":"MrDuan","phone":"13502074163","supplierCode":"","userId":"402848ad564f66df01564f66e0000000","username":"mrduan"}
     * dataOriginal : OMS
     * info : 用户登录成功！
     * returnCode : 200
     * token : eyJ1c2VybmFtZSI6Im1yZHVhbiIsInBhc3N3b3JkIjpbIjIiLCIwIiwiMSIsIjYiLCIwIiwiOCJdLCJyZW1lbWJlck1lIjpmYWxzZSwiaG9zdCI6IjEwNi4zOC4zMy4yNiJ9
     */

    private DataEntity data;
    private String dataOriginal;
    private String info;
    private int    returnCode;
    private String token;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setDataOriginal(String dataOriginal) {
        this.dataOriginal = dataOriginal;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataEntity getData() {
        return data;
    }

    public String getDataOriginal() {
        return dataOriginal;
    }

    public String getInfo() {
        return info;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getToken() {
        return token;
    }

    public static class DataEntity {
        /**
         * DRIVER_ID :
         * FID : ce66ebeb-f3b3-44f4-a614-fbad18af11da
         * IS_MODIFY_PWD : Y
         * MASTER_ID : bf891863-4c0c-46cb-b616-e3a16ebb7566
         * NAME : 朱海征（测试）
         * OMS_FID : 3996950
         * OMS_MASTER_ID : YAJ20160707A901013
         * PHONE : 15110272604
         * PLAT_FROM_ID : TJ001
         * PLAT_FROM_NAME : 天津网点
         * USER_TYPE : 1
         * authenticateCode :
         * businessUnitCode :
         * businessUnitName :
         * cusGroup :
         * customer_code :
         * customer_name :
         * enableCode :
         * isReviewFlag :
         * nickname : MrDuan
         * phone : 13502074163
         * supplierCode :
         * userId : 402848ad564f66df01564f66e0000000
         * username : mrduan
         */

        private String DRIVER_ID;
        private String FID;
        private String IS_MODIFY_PWD;
        private String MASTER_ID;
        private String NAME;
        private String OMS_FID;
        private String OMS_MASTER_ID;
        private String PHONE;
        private String PLAT_FROM_ID;
        private String PLAT_FROM_NAME;
        private String USER_TYPE;
        private String authenticateCode;
        private String businessUnitCode;
        private String businessUnitName;
        private String cusGroup;
        private String customer_code;
        private String customer_name;
        private String enableCode;
        private String isReviewFlag;
        private String nickname;
        private String phone;
        private String supplierCode;
        private String userId;
        private String username;

        public void setDRIVER_ID(String DRIVER_ID) {
            this.DRIVER_ID = DRIVER_ID;
        }

        public void setFID(String FID) {
            this.FID = FID;
        }

        public void setIS_MODIFY_PWD(String IS_MODIFY_PWD) {
            this.IS_MODIFY_PWD = IS_MODIFY_PWD;
        }

        public void setMASTER_ID(String MASTER_ID) {
            this.MASTER_ID = MASTER_ID;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public void setOMS_FID(String OMS_FID) {
            this.OMS_FID = OMS_FID;
        }

        public void setOMS_MASTER_ID(String OMS_MASTER_ID) {
            this.OMS_MASTER_ID = OMS_MASTER_ID;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public void setPLAT_FROM_ID(String PLAT_FROM_ID) {
            this.PLAT_FROM_ID = PLAT_FROM_ID;
        }

        public void setPLAT_FROM_NAME(String PLAT_FROM_NAME) {
            this.PLAT_FROM_NAME = PLAT_FROM_NAME;
        }

        public void setUSER_TYPE(String USER_TYPE) {
            this.USER_TYPE = USER_TYPE;
        }

        public void setAuthenticateCode(String authenticateCode) {
            this.authenticateCode = authenticateCode;
        }

        public void setBusinessUnitCode(String businessUnitCode) {
            this.businessUnitCode = businessUnitCode;
        }

        public void setBusinessUnitName(String businessUnitName) {
            this.businessUnitName = businessUnitName;
        }

        public void setCusGroup(String cusGroup) {
            this.cusGroup = cusGroup;
        }

        public void setCustomer_code(String customer_code) {
            this.customer_code = customer_code;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public void setEnableCode(String enableCode) {
            this.enableCode = enableCode;
        }

        public void setIsReviewFlag(String isReviewFlag) {
            this.isReviewFlag = isReviewFlag;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setSupplierCode(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDRIVER_ID() {
            return DRIVER_ID;
        }

        public String getFID() {
            return FID;
        }

        public String getIS_MODIFY_PWD() {
            return IS_MODIFY_PWD;
        }

        public String getMASTER_ID() {
            return MASTER_ID;
        }

        public String getNAME() {
            return NAME;
        }

        public String getOMS_FID() {
            return OMS_FID;
        }

        public String getOMS_MASTER_ID() {
            return OMS_MASTER_ID;
        }

        public String getPHONE() {
            return PHONE;
        }

        public String getPLAT_FROM_ID() {
            return PLAT_FROM_ID;
        }

        public String getPLAT_FROM_NAME() {
            return PLAT_FROM_NAME;
        }

        public String getUSER_TYPE() {
            return USER_TYPE;
        }

        public String getAuthenticateCode() {
            return authenticateCode;
        }

        public String getBusinessUnitCode() {
            return businessUnitCode;
        }

        public String getBusinessUnitName() {
            return businessUnitName;
        }

        public String getCusGroup() {
            return cusGroup;
        }

        public String getCustomer_code() {
            return customer_code;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public String getEnableCode() {
            return enableCode;
        }

        public String getIsReviewFlag() {
            return isReviewFlag;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPhone() {
            return phone;
        }

        public String getSupplierCode() {
            return supplierCode;
        }

        public String getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }
    }
}

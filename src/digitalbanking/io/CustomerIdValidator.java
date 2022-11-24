/**
 * purpose: xử lý nghiệp vụ căn cước công dân
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.io;

public class CustomerIdValidator {
    //mảng chứa thông tin tỉnh và mã tỉnh ứng với index
    private static final String[] TINH = new String[]{null, "Hà Nội", "Hà Giang", null, "Cao Bằng", null, "Bắc Kạn", null, "Tuyên Quang", null, "Lào Cai", "Điện Biên", "Lai Châu", null, "Sơn La", "Yên Bái", null, "Hòa Bình", null, "Thái Nguyên", "Lạng Sơn", null, "Quảng Ninh", null, "Bắc Giang", "Phú Thọ", "Vĩnh Phúc", "Bắc Ninh", null, null, "Hải Dương", "Hải Phòng", null, "Hưng Yên", "Thái Bình", "Hà Nam", "Nam Định", "Ninh Bình", "Thanh Hóa", null, "Nghệ An", null, "Hà Tĩnh", null, "Quảng Bình", "Quảng Trị", "Thừa Thiên Huế", null, "Đà Nẵng", "Quảng Nam", null, "Quảng Ngãi", "Bình Định", null, "Phú Yên", null, "Khánh Hòa", null, "Ninh Thuận", null, "Bình Thuận", null, "Kon Tum", null, "Gia Lai", null, "Đắk Lắk", "Đắk Nông", "Lâm Đồng", null, "Bình Phước", null, "Tây Ninh", null, "Bình Dương", "Đồng Nai", null, "Bà Rịa - Vũng Tàu", null, "Hồ Chí Minh", "Long An", null, "Tiền Giang", "Bến Tre", "Trà Vinh", null, "Vĩnh Long", "Đồng Tháp", null, "An Giang", null, "Kiên Giang", "Cần Thơ", "Hậu Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau"};

    //phương thức kiểm tra CCCD hợp lệ
    public static boolean validateCustomerId(String canCuocCongDan) {
        //kiểm tra kích thước chuỗi = 12
        if (canCuocCongDan.length() != 12) {
            return false;
        }

        //kiểm tra chuỗi có phải chuỗi số
        for (int i = 0; i < canCuocCongDan.length(); i++) {
            try {
                Integer.parseInt(canCuocCongDan.substring(i, i + 1));
            } catch (Exception errror) {
                return false;//chuỗi có một ký tự không phải số nguyên
            }
        }

        //kiểm tra mã tỉnh trong số CCCD có hợp lệ hay không
        int maTinh = Integer.parseInt(canCuocCongDan.substring(0, 3));
        if (maTinh >= TINH.length) {
            return false;//mã tỉnh không tồn tại
        }
        return TINH[maTinh] != null;
    }
}

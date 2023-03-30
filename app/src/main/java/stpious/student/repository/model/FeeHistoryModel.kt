package stpious.student.repository.model

import com.google.gson.annotations.SerializedName

data class FeeHistoryModel(

    @SerializedName("SystemReceiptNo" ) var SystemReceiptNo : Int?    = null,
    @SerializedName("PayingAmount"    ) var PayingAmount    : Int?    = null,
    @SerializedName("payMode"         ) var payMode         : String? = null,
    @SerializedName("ReceiptDate"     ) var ReceiptDate     : String? = null)

data class FeePaymentModel(

    @SerializedName("YearNo" ) var YearNo : Int?    = null,
    @SerializedName("Semester"    ) var Semester    : String?    = null,
    @SerializedName("TotalAssignedFee"    ) var TotalAssignedFee    : Float?    = null,
    @SerializedName("TotalPaidAmount"    ) var TotalPaidAmount    : Float?    = null,
    @SerializedName("TotalDue"    ) var TotalDue    : Float?    = null,)

data class GalleryModel(@SerializedName("ImageUrl"    ) var ImageUrl    : String?    = null,
                        @SerializedName("ImageName"    ) var ImageName    : String?    = null)

data class NoticeModel(@SerializedName("CircularSubject") var CircularSubject: String? = null,
                       @SerializedName("FilePath") var FilePath: String? = null,
                       @SerializedName("CreatedDate") var CreatedDate: String? = null)

data class SyllabusModel(@SerializedName("AcademicYearRegulation") var AcademicYearRegulation: String? = null,
                       @SerializedName("GroupName") var GroupName: String? = null,
                       @SerializedName("SemesterNo") var SemesterNo: String? = null,
                       @SerializedName("SubjectName") var SubjectName: String? = null,
                       @SerializedName("FilePath") var FilePath: String? = null)

data class TimeTableModel(@SerializedName("PeriodName") var PeriodName: String? = null,
                       @SerializedName("Duration") var Duration: String? = null,
                       @SerializedName("EmployeeName") var EmployeeName: String? = null,
                       @SerializedName("SubjectName") var SubjectName: String? = null)
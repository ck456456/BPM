session
	G_F02		: BPMF02 物件
	formID		: 表單代號
	G_SubMenu	: 功能選單
	 
1. 表單定義 (formid): 表單初始定義 
2. 表單記錄 (formid+formsn) : 表單目前狀態紀錄
3. 表單流程 (formid+formsn+flowid+flowsn)+人員 : 表單應執行流程紀錄
4. 流程歷史 (formid+formsn+flowid+flowsn+recordid)+人員 : 流程歷史紀錄

-----------------------------------------------------------------------
jcifs "HTTP Filter not supporting NTLMv2" 

win7 與 VISTA 無法認證問題
原因 : 是這兩版默認為 "NTLM v2"
解決 : 直接將 LmCompatibilityLevel 修改為 1 即可 
		參考 : 	http://technet.microsoft.com/en-us/library/cc960646.aspx
				http://yuelin.pixnet.net/blog/post/2803631-%E4%BF%AE%E6%94%B9windows-vista-%26-win7-home-premium%E3%80%81home-basic-ntlm-
				http://technet.microsoft.com/zh-tw/library/dd566199(v=ws.10).aspx 
NTLM v2 改為  NTLM

VISTA :
	[HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Lsa]
	“lmcompatibilitylevel“=dword：00000003
	NTLM V2為00000003 
	NTLM只要將值改為00000001即可
	
win7 :
	我發現~在[HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Lsa]
	這個路徑下已經沒有“lmcompatibilitylevel“這個DWORD值了
	所以自己新增一個DWORD 叫“lmcompatibilitylevel“再把值設為00000001
	這樣就OK了~~
	
	設定 [網路安全性: 允許 Local System 對 NTLM 使用電腦身分識別] 安全性原則設定，允許使用 Negotiate 的 Local System 服務在還原至 NTLM 驗證時使用電腦身分識別。
	如果您沒有設定此原則設定，則連線至 Windows Vista 或 Windows Server 2008 之前版本的 Windows 作業系統時，以 Local System 身分執行並使用預設認證及 NULL 工作階段的服務就會還原至 NTLM 驗證。這可能會造成 Windows 作業系統間的某些驗證要求失敗，進而顯示錯誤。
-----------------------------------------------------------------------
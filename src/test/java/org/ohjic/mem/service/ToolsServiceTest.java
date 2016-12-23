package org.ohjic.mem.service;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ohjic.mem.common.GOODS;
import org.ohjic.mem.dao.ChurchUseNumberMapper;
import org.ohjic.mem.model.ChurchUseNumber;
import org.ohjic.mem.model.Churchinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
								 "file:src/main/webapp/WEB-INF/spring/appServlet/dao-context_live.xml"})
public class ToolsServiceTest {

	@Autowired
	private ToolsService toolsService;
	
	/**
	 * 교회정보를 삭제한다.
	 */
	@Test
	public void testRemoveChurch() {

//		Integer churchCode = 3194; // 3194 열국교회
//		Integer churchCode = 6068; // 효성교회 (6068)
//		Integer churchCode = 6051; // 6051 사랑의교회
//		Integer churchCode = 5192; // 5192 참사랑교회
//		Integer churchCode = 1786; // 1786 인천서부교회
//		Integer churchCode = 1782; // 1782 천군교회
//		Integer churchCode = 5192; // 5192:참사랑교회
//		Integer churchCode = 6064; // 6064:참사랑교회
//		Integer churchCode = 3194; // 3194:열국교회
//		Integer churchCode = 6094 ; // 6094:김지훈
//		Integer churchCode = 63 ; // 63:은평교회3부청소년부(테스트용)
//		Integer churchCode = 1859 ; // 1859:시민교회
//		Integer churchCode = 6154 ; // 진성교회
//		Integer churchCode = 845 ; // 삼양교회
//		Integer churchCode = 1616 ; // 여수명은교회
//		Integer churchCode = 5480 ; // 5480 성광교회
//		Integer churchCode = 6184 ; // 오직교회
//		Integer churchCode = 5330 ; // 의왕우리교회
//		Integer churchCode = 3844 ; // 3844 자인제일교회 
//		Integer churchCode = 1016 ; // 1016 시온성교회
//		Integer churchCode = 6184 ; // 6184 오직교회
//		Integer churchCode = 6241 ; // 주북교회-유년부-6241
//		Integer churchCode = 6240 ; // 주북교회-중등부-6240 
//		Integer churchCode = 6243 ; // 주북교회-초등부-6243
//		Integer churchCode = 4949 ; // 성은교회
//		Integer churchCode = 2054 ; // 온양신광교회청년부(2054
//		Integer churchCode = 4603 ; // 춘천온누리교회중등부(4603), 
//		Integer churchCode = 5886 ; // 연무제일교회5886
		Integer churchCode = 928 ; // 928 재건중앙교회 (무료사용)
		boolean isFree = true;		// 무료 사용여부

		toolsService.removeChuch(churchCode, isFree);
		
	}
	
	/**
	 * 가족 관계 재설정
	 */
	@Test
	public void testReadustFamily() {
//		Integer churchCode = 2393;
//		Integer churchCode = 6233;
//		Integer churchCode = 5291;	// 장유대성교회
		Integer churchCode = 3883;	// 분당우리교회
		toolsService.readjustFamily(churchCode);
		
	}
	
	/**
	 * 관리자 아이디 변경 쿼리 
	 */
	@Test
//	@Test(expected=Exception.class)
	public void testChangeChurchAdminId() {
		
		Integer churchCode = 5814;
		String oldId = "wooridlechurch22";
		String newId = "wooridlechurch";
		
		toolsService.changeChurchAdminId(churchCode, oldId, newId);
		
		
	}
	
	/**
	 * 교육 수강인원 현재로 맞춰주기
	 */
	@Test
	public void testReadjustEduCount() {
		
		Integer churchCode = 5814;
		boolean result =toolsService.readjustEduCount(churchCode);
		
		assertTrue(result);
	}
	
	
	/**
	 * 코회 nameCode에 visitCategory 데이터 있는지 여부 확인 
	 */
	@Test
	public void testHasNameCodeForVisitCategory() {
		
		List<Churchinfo> churchCodeList = toolsService.getChurchInfoList();
		List<Churchinfo> hasNotChurch = new ArrayList<>();
		
		for (Churchinfo churchinfo : churchCodeList) {
			int result = toolsService.hasNameCodeForVisitCategory(churchinfo.getChurchCode());
			
			if(result != 3 && result > -1) {
//				System.out.println(churchinfo.getChurchName() + ": " + churchinfo.getChurchCode());
				hasNotChurch.add(churchinfo);
			}
		}
		
		for (Churchinfo churchinfo : hasNotChurch) {
			System.out.println(churchinfo.getChurchName() + ": " + churchinfo.getChurchCode());
		}
	}
	
	/**
	 * 디렉토리 내의 모든 파일목록의 파일명의 일부를 replace함으로  변경한다.
	 * replace된 파일들은 해당 디렉토리 내의 rename 디렉토리에 복사된다.
	 * toolsService.renameFilesInDirectory(orginalDirStr, "S", "1");
	 * ex) S0061000 => 10061000
	 * 
	 */
	@Test
	public void testRenameByReplacingFilesInDirectory() {
		String orginalDirStr = "C:/Users/ohjic/Downloads/70000";
		boolean result = toolsService.renameByReplacingFilesInDirectory(orginalDirStr, "S", "1");
		
		assertTrue(result);
	}
	
	@Test
	public void testRenameFilesInDirectory() {
		String orginalDirStr = "C:/Users/ohjic/Documents/juan/사진";
		
		boolean result = toolsService.renameFilesInDirectory(orginalDirStr, "S", "1");
		
		assertTrue(result);
	}
	
	/**
	 * 문자인증이 되지 않는 교회를 수동으로 문자인증 처리한다.
	 */
	@Test
	public void testAuthSmsForChurch() {
		
		String churchNam = "오륜교회";
		String churchPhoneNumber ="024854004 ";
		List<String> userIdList = new ArrayList<>();
		userIdList.add("dbswnssla");
		
		boolean result = toolsService.authSmsForChurch(churchNam, churchPhoneNumber, userIdList);
		
		assertTrue(result);
	}
	
	@Test
	public void testGenerateKyoEncryptedPassword() {
		
//		String plain = "1004";
//		String plain = "7511"; // c472207ab6bdc021ae67cbe471fcf1ad
//		String plain = "0930"; // fa641e55aad0372b6f9d150d67b3b8fc 기존:26f09498bc5fd1505d5784e0e78a462d
//		String plain = "2053"; // b71b61c92e43750f9127806dd87085fa
//		String plain = "0298"; // 35ef2e86354c4c8978a022e865d28af5
//		String plain = "5755"; // 5295ad31b07037cd3bc2bb528ebdbf2f
//		String plain = "7954"; // ff49426aa6ab92dbbf8f13b39b655ff4
//		String plain = "0863"; // 6131cca989256bf7992c14ff0bd22de6
//		String plain = "9170"; // 853bb16a7e800261fc1a1afa45ed31d5
//		String plain = "2701"; // 7ff1f83e78bd3da1d7468c37a9b6bc02
		String plain = "1111"; // a0ca9ad87e220dd9ee97c32f94142afc
		String result = toolsService.generateKyoEncryptedPassword(plain);
		System.out.println("generated password: " + result);
//		assertTrue("18a89831df46bf18809d958047555aaf".equals(result));
	}
	
	// INSERT INTO `ohjic`.`portal_user` (`user_id`, `user_name`, `jumin`, `secureKey`, `user_passwd`, `birth_year`, `birth_date`, `birth_solar`, `user_zipcode`, `user_address1`, `user_address2`, `user_email`, `position`, `regi_date`, `paster_name`, `email_agree`, `sms_agree`, `admin`, `withdraw`, `point`, `user_address_street`) VALUES ('hanwooricalgar', '김재윤', '', '0', 'c472207ab6bdc021ae67cbe471fcf1ad', '1964', '6', '14', '-', '', '', '', '', '1473145192', '김재윤목사님', '0', '0', '0', '0', '0', '');
	
	@Test
	public void testBackupMemberImage() {
		
//		String plain = "1004";
		String plain = "4406"; // c472207ab6bdc021ae67cbe471fcf1ad
		String result = toolsService.generateKyoEncryptedPassword(plain);
		System.out.println("generated password: " + result);
//		assertTrue("18a89831df46bf18809d958047555aaf".equals(result));
	}
	
	@ Test
	public void testGetKyoTableList() {
		
		int churchCode = 6100;
		Map<Integer, Integer> churchList = new HashMap<>();
		
		for (int i = 0; i < churchCode; i++) {
			try {
				List<Map<String, Object>> tableList = toolsService.getKyoTableList(i);
				System.out.println("churchCode : " + i + ", tableCount: " + tableList.size());
				churchList.put(i, tableList.size());
			}catch (Exception e) {
				System.out.println("churchCode is not exist" );
			}
		}
		
		Set<Integer> keySet = churchList.keySet();
		for (Integer integer : keySet) {
			int tableCount = churchList.get(integer);
			if(tableCount > 0 && tableCount<107) {
				System.out.println("churchCode : "+integer + ", count: " +churchList.get(integer));
			}
		}
		
	}
	
	
	@ Test
	public void testGetKyoTableColumnList() {
		
		int churchCode = 6100;
		Map<Integer, Integer> churchList = new HashMap<>();
		
		for (int i = 0; i < churchCode; i++) {
			try {
				String tableName = "member";
				List<Map<String, Object>> tableColumnList = toolsService.getTableColumnList(i, tableName);
//				System.out.println("churchCode : " + i + ", tableColumnCount: " + tableList.size());
				churchList.put(i, tableColumnList.size());
			}catch (Exception e) {
				System.out.println("churchCode is not exist" );
			}
		}
		
		Set<Integer> keySet = churchList.keySet();
		for (Integer integer : keySet) {
			int tableCount = churchList.get(integer);
			if(tableCount != 0 && tableCount != 101) {
				System.out.println("churchCode : "+integer + ", tableColumnCount: " +churchList.get(integer));
			}
		}
		
	}
	
	
	@ Test
	public void testRegistMemberGuide() {
		
//		{1037,1054,1139,1183,1199,1223,1293,130,1331,1337,1362,1372,1388,1433,1446,1482,152,1568,158,1580,1581,163,1685,1690,1700,1708,1725,1775,1796,1811,1899,2005,2018,2019,2112,2113,2115,2151,216,2226,2238,2255,2261,2274,2286,2288,2289,2310,2324,234,2342,2350,2371,2382,2411,2433,2444,245,2478,2503,2509,2510,2512,2539,2547,2588,2590,2623,2629,2631,2638,2664,267,2727,28,2836,2878,2903,2972,2983,3006,3007,3013,3022,3042,3061,3070,3078,3087,3092,3094,3106,3125,3128,3130,3164,3165,3186,3189,3190,3193,3206,3215,3227,3233,3234,3241,3263,3287,3304,3310,3311,3322,3337,3348,3372,3374,3383,3388,3400,3403,3404,3410,3469,3485,3497,3498,3500,351,3522,3524,3525,3527,3529,353,3535,3539,3544,3547,3548,3549,3560,3561,3563,3564,3577,3579,3589,3603,3609,3621,3633,3642,3648,3670,3671,3688,3700,3701,3705,3707,3740,3742,3749,3761,3762,3784,3789,3794,3797,3809,3814,3820,3826,3833,3882,3902,3909,3919,3922,3925,3929,3943,396,3965,3974,3975,3997,4010,4031,4045,4056,4059,4061,4064,4068,4071,4072,408,4080,4086,4093,4094,4097,4108,4116,4121,4133,4150,4184,4190,4199,4213,4217,427,4275,4298,4307,4308,4325,4326,4359,4365,4407,4409,4416,4428,4431,4435,4451,4465,4468,4501,4529,4531,4532,4572,4591,46,4604,4616,4640,4644,4652,4667,4680,4700,4716,4728,4732,4741,4750,476,4762,4772,4793,4794,4815,4822,4831,4834,4835,4864,4878,4884,4889,4915,4920,4929,4959,4960,4981,4983,4995,5015,5031,5072,5078,5080,5125,5151,5153,5155,5160,5180,5199,5200,5227,5233,5235,5245,5254,5272,5276,5277,5280,5284,5287,5290,5305,5328,5339,539,5416,5426,544,5505,5589,565,5703,5750,5774,5780,5807,5809,5814,5818,5839,5846,5909,5930,5977,5986,6,6007,6011,6026,6028,6075,6090,6095,614,636,703,710,748,791,793,800,802,817,902,937,999};
		Integer[] churchCodeList = {5059, 5228, 6037, 6092};
		
		Map<Integer, Integer> reusltList = new HashMap<>();
		for (Integer churchCode : churchCodeList) {
			try {
				int resultCount = toolsService.registMemberGuide(churchCode);
				reusltList.put(churchCode, resultCount);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		Set<Integer> keySet = reusltList.keySet();
		for (Integer key : keySet) {
			int resultCount = reusltList.get(key);
			System.out.println("churchCode : "+key + ", resultCount: " +resultCount);
			
		}
		
		
	}
	
	@ Test
	public void testModifyUpdateDateForMemberGuide() {
		
		Integer[] churchCodeList = {1037,1054,1139,1183,1199,1223,1293,130,1331,1337,1362,1372,1388,1433,1446,1482,152,1568,158,1580,1581,163,1685,1690,1700,1708,1725,1775,1796,1811,1899,2005,2018,2019,2112,2113,2115,2151,216,2226,2238,2255,2261,2274,2286,2288,2289,2310,2324,234,2342,2350,2371,2382,2411,2433,2444,245,2478,2503,2509,2510,2512,2539,2547,2588,2590,2623,2629,2631,2638,2664,267,2727,28,2836,2878,2903,2972,2983,3006,3007,3013,3022,3042,3061,3070,3078,3087,3092,3094,3106,3125,3128,3130,3164,3165,3186,3189,3190,3193,3206,3215,3227,3233,3234,3241,3263,3287,3304,3310,3311,3322,3337,3348,3372,3374,3383,3388,3400,3403,3404,3410,3469,3485,3497,3498,3500,351,3522,3524,3525,3527,3529,353,3535,3539,3544,3547,3548,3549,3560,3561,3563,3564,3577,3579,3589,3603,3609,3621,3633,3642,3648,3670,3671,3688,3700,3701,3705,3707,3740,3742,3749,3761,3762,3784,3789,3794,3797,3809,3814,3820,3826,3833,3882,3902,3909,3919,3922,3925,3929,3943,396,3965,3974,3975,3997,4010,4031,4045,4056,4059,4061,4064,4068,4071,4072,408,4080,4086,4093,4094,4097,4108,4116,4121,4133,4150,4184,4190,4199,4213,4217,427,4275,4298,4307,4308,4325,4326,4359,4365,4407,4409,4416,4428,4431,4435,4451,4465,4468,4501,4529,4531,4532,4572,4591,46,4604,4616,4640,4644,4652,4667,4680,4700,4716,4728,4732,4741,4750,476,4762,4772,4793,4794,4815,4822,4831,4834,4835,4864,4878,4884,4889,4915,4920,4929,4959,4960,4981,4983,4995,5015,5031,5072,5078,5080,5125,5151,5153,5155,5160,5180,5199,5200,5227,5233,5235,5245,5254,5272,5276,5277,5280,5284,5287,5290,5305,5328,5339,539,5416,5426,544,5505,5589,565,5703,5750,5774,5780,5807,5809,5814,5818,5839,5846,5909,5930,5977,5986,6,6007,6011,6026,6028,6075,6090,6095,614,636,703,710,748,791,793,800,802,817,902,937,999, 5059, 5228, 6037, 6092};
		
		Map<Integer, Integer> reusltList = new HashMap<>();
		for (Integer churchCode : churchCodeList) {
			try {
				int resultCount = toolsService.modifyUpdateDateForMemberGuide(churchCode);
				reusltList.put(churchCode, resultCount);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		Set<Integer> keySet = reusltList.keySet();
		for (Integer key : keySet) {
			int resultCount = reusltList.get(key);
			System.out.println("churchCode : "+key + ", resultCount: " +resultCount);
			
		}
		
		
	}
	
	/**
	 * 대시보드의 제목(title) 변경(통일)
	 *  update dashboard_portlet set title = '이번주 새 등록자' where name = 'registration_members';
		update dashboard_portlet set title = '이번주 새 환영자' where name = 'reception_members';
	 */
	
	@Test
	public  void testModifyDashboardTitle() {
		
		int maxChurchCode = 6138;
		
		for (int i = 0; i < maxChurchCode; i++) {
			
			try {
			Integer churchCode =  i;
				String name = "registration_members";
				String title = "이번주 새 등록자";
				
				toolsService.modifyDashboardTitleByName(churchCode, name, title);
				
				name = "reception_members";
				title = "이번주 새 환영자";
				
				toolsService.modifyDashboardTitleByName(churchCode, name, title);
			}catch(Exception e) {
				System.out.println("This church is not exist..");
			}
		}
	}
	
	@Autowired
	ChurchUseNumberMapper churchUseNumberMapper;
	
	@Test
	public void testGetChurchListDetail() {
		
		List<Map<String, Object>> result = toolsService.getChurchListDetail();
		
		for (Map<String, Object> churchDatail : result) {
			
			String churchCode = String.valueOf(churchDatail.get("churchCode"));
			BigDecimal kyoNumber = (BigDecimal) churchDatail.get("교인");
			String churchName = (String) churchDatail.get("churchName");
//			Integer limit = churchDatail.containsKey("limit") ?  (int) churchDatail.get("limit"):null;
			
			ChurchUseNumber churchUseNumber = new ChurchUseNumber();
			churchUseNumber.setChurchCode(churchCode);
			churchUseNumber.setKyoNumber(kyoNumber.intValue());
			churchUseNumber.setChurchName(churchName);
//			churchUseNumber.setLimit(limit);
			
			churchUseNumberMapper.insertSelective(churchUseNumber);
			
		}
/*		String resultJson = new Gson().toJson(result);
		
		
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter( new FileWriter( "C:\\Users\\ohjic\\Documents\\ohjic_stat"));
		    writer.write( resultJson);

		} catch ( IOException e) {
			
		} finally {
		    try {
		        if ( writer != null)
		        writer.close( );
		    } catch ( IOException e) {
		    	
		    }
		}
		*/
	}
	
	@Test
	public void testModifyMemberCardView() {
		
		Integer limitChurchCode = 6118;
		
		for(int i=0;i<limitChurchCode;i++) {
			Integer churchCode = i;
			
			try { 
				
				List<Map<String, Object>> viewList =  toolsService.getMemberCardViewListByChurchCode(churchCode);
				
				for (Map<String, Object> view : viewList) {
					String viewColumn =  (String) view.get("view_column");
					Long viewNo =  (Long) view.get("view_no");
					System.out.println("before: " + viewColumn);
					
					try {
						JSONObject json = new JSONObject(viewColumn);
						String[] removeColumList = {"guide", "guide2", "guide_phone", "guide_relation"};
						
						for (String removeColum : removeColumList) {
							if(json.has(removeColum)) {
								json.remove(removeColum);
							}
						}
						
						view.put("view_column", json.toString());
						boolean result = toolsService.modifyMemberCardView(churchCode, view );
						
						System.out.println("churchCode: " + churchCode +", view_no:" + viewNo + ", result: "+ result);
					}catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
	
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	
	@Test
	public void testGetManagerById() {
		
		String managerId = "young1960";
		List<Integer> resultList = new ArrayList<>();
		
		for (int i = 0 ; i<6170;i++) {
			try {
				Integer churchCode = i;
				Map<String, Object> manager = toolsService.getManagerById(churchCode, managerId);
				if(manager !=null) {
					System.out.println("managerId는  churchCode가 "+ i+ "에 속해 있습니다." );
					resultList.add(churchCode);
				}
			}catch (Exception e) {
				
			}
		}
		
		for (Integer result : resultList) {
			System.out.println("managerId는  churchCode가 "+ result+ "에 속해 있습니다." );
		}
	}
	
	@Test
	public void testGetChurchManagerByChurchUseNumber() {
		
		List<Map<String, Object>> churchCodeList = toolsService.getChurchManagerByChurchUseNumber(2000);
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		for (Map<String, Object> church : churchCodeList) {
			
			try {
				
				Integer churchCode = Integer.parseInt((String)church.get("church_code"));
				System.out.println("churchCode: " + churchCode);
				Map<String, Object> superAdmin = toolsService.getSuperAdminByChurchCode(churchCode);
				
				superAdmin.put("version", church.get("version").toString());
				superAdmin.put("status", church.get("status").toString());
				superAdmin.put("church_code", church.get("church_code").toString());
				superAdmin.put("church_name", church.get("church_name").toString());
				
				resultList.add(superAdmin);
				
			}catch(Exception e) {
				
			}
			
		}
		
		String fileName = "C:\\Users\\ohjic\\Documents\\ohjic_super_admin.txt";
		toJsonFile(resultList, fileName );
		
	}

	private void toJsonFile(Object resultList, String fileName) {
		String resultJson = new Gson().toJson(resultList);
		
		
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter( new FileWriter( fileName));
		    writer.write( resultJson);

		} catch ( IOException e) {
			
		} finally {
		    try {
		        if ( writer != null)
		        writer.close( );
		    } catch ( IOException e) {
		    	
		    }
		}
	}
	
	@Test
	public void testRestistCommonCode() {
		
		int limitChurchCode = 6181;
		
		for (int i = 1; i < limitChurchCode; i++) {
			
			try {
				Integer churchCode = i;
				String name = "전화심방";
//				List<Map<String, String>> commonCodeList = toolsService.getCommonCodeByName(churchCode, name);
				
//				if(commonCodeList.size() == 0 ) {
					//String ord = String.valueOf(commonCodeList.get(0).get("ord") + 1);
//					Map<String,String> telVisit = new HashMap<>();
//					telVisit.put("depth1_name", name);
//					telVisit.put("depth2_name", "");
//					telVisit.put("depth", "1");
//					telVisit.put("name", name);
//					telVisit.put("kind", "visit");
//					telVisit.put("level", "0");
//					boolean registResult = toolsService.registCommonCode(churchCode, telVisit );
					boolean modifyResult = toolsService.modifyCommonCodeDepth(churchCode, name);
//				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		}
	}
	

	@Test
	public void testAddGoodsAttCheck() {
		
//		Integer churchCode = 10181;
//		Integer churchCode = 1690;
		Integer churchCode = 3986; // 목동반석교회
		
		GOODS goods = GOODS.att_check;
		String domain="mpetra2";
		boolean result = toolsService.addGoods(churchCode, domain, goods);
		
		
		assertTrue(result);
		
	}
	
	@Test
	public void testAddGoodsSmartCall() {
		
		Integer churchCode = 3986; // 목동반석교회
		
		GOODS goods = GOODS.smart_call;
		String domain="";
		boolean result = toolsService.addGoods(churchCode, domain, goods);
		
		assertTrue(result);
		
	}
	
	@Test
	public void test소천2depth() {
		
		List<Map<String, Object>> exsistedList = new ArrayList<>();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		String groupName="소천";
		
		for (int i = 6210; i < 6293; i++) {
			Integer churchCode = i;
			try {
				Map<String, Object> result = toolsService.getCgroupByGroupName(churchCode, groupName);
//				result.put("churchCode", String.valueOf(churchCode));
				
//				System.out.println(result.toString());
				if(result!=null) {
					result.put("churchCode", String.valueOf(churchCode));
					exsistedList.add(result);
				}else {
					Map<String, Object> tmp = null;
					if(toolsService.registSocheon(churchCode)){
						tmp =new HashMap<>();
						tmp.put("churchCode", churchCode);
						resultList.add(tmp);
					}
				}
				
			}catch(Exception e) {
				
			}
		}

		
		String fileName = "C:\\Users\\ohjic\\Documents\\ohjic_exsited_socheon.txt";
		toJsonFile(exsistedList, fileName );
		
		String fileName2 = "C:\\Users\\ohjic\\Documents\\ohjic_add_socheon.txt";
		toJsonFile(resultList, fileName2 );
	}
	
	@Test
	public void test소천1depth() {
		
		List<Map<String, Object>> exsistedList = new ArrayList<>();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		String groupName="소천";
		
		for (int i = 0; i < 6300; i++) {
			Integer churchCode = i;
			try {
				Map<String, Object> result = toolsService.getCgroupByGroupName(churchCode, groupName);
//				result.put("churchCode", String.valueOf(churchCode));
				
//				System.out.println(result.toString());
				if(result!=null) {
					result.put("churchCode", String.valueOf(churchCode));
					exsistedList.add(result);
				}else {
					Map<String, Object> tmp = null;
					if(toolsService.registSocheon1(churchCode)){
						tmp =new HashMap<>();
						tmp.put("churchCode", churchCode);
						resultList.add(tmp);
					}
				}
				
			}catch(Exception e) {
				
			}
		}

		
		String fileName = "C:\\Users\\ohjic\\Documents\\ohjic_exsited_socheon.txt";
		toJsonFile(exsistedList, fileName );
		
		String fileName2 = "C:\\Users\\ohjic\\Documents\\ohjic_add_socheon.txt";
		toJsonFile(resultList, fileName2 );
	}
	
	@Test
	public void test한글이름_영문이름() {
		
		List<List<Map<String, Object>>> resultList = new ArrayList<>();
		
		for (int i = 0; i < 6210; i++) {
			Integer churchCode = i;
			try {
				List<Map<String, Object>> result = toolsService.getMemberNameHanAndEnCount(churchCode);
				
				resultList.add(result);
//				for (Map<String, Object> map : result) {
//					
//					System.out.println("han: "+ (int)map.get("name"));
//					System.out.println("en: "+ (int)map.get("name"));
//					//if((int)map.get("name") >0 ||(int)map.get("name")>0) {
//						map.put("churchCode", churchCode);
//						resultList.add(map);
//					//}
//					
//				}
				
			}catch(Exception e) {
				
			}
		}

		String fileName = "C:\\Users\\ohjic\\Documents\\ohjic_han_en_member_list.txt";
		toJsonFile(resultList, fileName);
	}
}
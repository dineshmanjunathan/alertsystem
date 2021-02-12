<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">

function getSponserName() {
    	$.ajax({
            url: "/get/sponser",
            data: {
                "sponserId": document.getElementById("referedby").value
            },
            type: "get",
            cache: false,
            success: function (data) {

            	if(data.length>0) {
                	var s = document.getElementById("sponsername"); s.value = data;
                	$("#errmsg").text("");
                }else{
                	$("#errmsg").text("Invalid Sponsor Id");
                	$("span").css("color", "red");
                    document.getElementById("errmsg").style.visibility = "visible";
                    document.getElementById("sponsername").value='';
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('ERROR:' + XMLHttpRequest.status + ', status text: ' + XMLHttpRequest.statusText);
            }
        });
}
function enableTermsAndCond(){
	var checker = document.getElementById('terms').checked;
	var prichecker = document.getElementById('pripolicy').checked;
	var bt = document.getElementById('btSubmit');
	if(checker && prichecker){
		bt.disabled = false;
	} else {
		bt.disabled = true;
	}
}

function checkPhNumExists() {
	$.ajax({
        url: "/member/phNoExists",
        data: {
            "phonenumber": document.getElementById("phonenumber").value
        },
        type: "get",
        cache: false,
        success: function (data) {
        	if(data.length>0) {
        		$("#errmsgmobno").text("Mobile Number Already Exists");
            	$("span").css("color", "red");
                document.getElementById("errmsgmobno").style.visibility = "visible";
                document.getElementById("phonenumber").value='';
            }else{
            	var s = document.getElementById("phonenumber"); 
            	s.value = data;
            	$("#errmsgmobno").text("");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('ERROR:' + XMLHttpRequest.status + ', status text: ' + XMLHttpRequest.statusText);
        }
    });
}
</script>
</head>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-1 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<li class="active"><a href="">Member Registration</a></li>
					</ul>
				 <div class="payment-adress">
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/index.jsp" type="submit" name="submit"
							value="adminListing">Back</a>
					</div>

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="review-content-section">
										<form action="/register" method="post">

											<p style="color: red" align="center">${errormsg}</p>

											<input type="hidden" name="id" id="id" value="${member.id}">
											<input type="hidden" name="role" id="role" value="MEMBER">

											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<p style="color: red" align="center">${deletesuccessmessage}</p>
												
												<li class="active"><a >Sponsor Details:</a></li>
												
												<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
													<c:choose>
													<c:when test="${not empty member}">
													<div class="form-group">
																	<input name="referedby" id="referedby" type="text" onblur="getSponserName();"
																		class="form-control" placeholder="Sponsor Id"
																		value="${member.referedby}" readonly>
													</div>
													</c:when>
													<c:otherwise>
													<div class="form-group">
																	<input name="referedby" id="referedby" type="text" onblur="getSponserName();"
																		class="form-control" placeholder="Sponsor Id"
																		value="${member.referedby}" required>
													</div>
													</c:otherwise>
													</c:choose>
													<span id="errmsg"></span>
													</div>
												<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
												<div class="form-group">
																	<input name="sponsername" id="sponsername" type="text"
																		class="form-control" placeholder="Sponsor Name"
																		value="${SPONSERNAME}" readonly required>
												</div>
												</div>
												</div>
												<li class="active"><a >User Details:</a></li>
												

												<div class="well row">
														<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>
														<div class="form-group">
															<input name="name" type="text" class="form-control"
																placeholder="Name as Per PAN Card" value="${member.name}"
																required>
														</div>

														<div class="form-group">
															<input name="password" type="password" 
																class="form-control" placeholder="Password" minlength="8"  maxlength="10"
																value="${member.password}" required>
														</div>
														<div class="form-group">
															<input name="email" type="email" class="form-control"
																placeholder="Email" value="${member.email}">
														</div>
													<br>													
													</div>
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group">
															<label>Date of Birth:</label> <input name="dob"
																type="date" class="form-control"
																placeholder="Date of Birth" value="${member.dob}"
																required>
														</div>

														<div class="form-group">
															<label>Gender :</label> <input name="gender"
																class=" required " id="gender" type="radio" value="Male"
																${member.gender eq 'Male' ?'Checked':''}>Male <input
																name="gender" class=" required " id="gender"
																type="radio" value="Female"
																${member.gender eq 'Female' ?'Checked':''}>Female
														</div>

														<div class="form-group">
															<input name="phonenumber" type="text" id="phonenumber"
																class="form-control" placeholder="Phone Number"
																value="${member.phonenumber}"  required onblur="checkPhNumExists();">
														</div>
														<span id="errmsgmobno"></span>

													</div>
														</div>
														<div class="form-group">
														<input type="checkbox" name="pripolicy" id="pripolicy" value="Privacy Policy" onclick="enableTermsAndCond();" > I have read and agree to the following 
														<a target="_blank" data-toggle="modal" data-target="#PrivacyPolicy" rel="nofollow noopener ugc" >Privacy Policy</a>
													</div>
  
													<div class="form-group">
														<input type="checkbox" name="terms" id="terms" value="Conditions" onclick="enableTermsAndCond();"> I have read and agree to the following 
														<a target="_blank" data-toggle="modal" data-target="#Termsandconditions" rel="nofollow noopener ugc" >Terms and conditions</a>
													</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="payment-adress">
														<button class="rmk btn btn-primary waves-effect waves-light"
															type="submit" name="submit" id="btSubmit" value="register" disabled>Submit</button>
														<button class="rmk btn btn-primary waves-effect waves-light"
															type="reset" name="reset" value="reset">Clear</button>

													</div>
												</div>
											</div>

										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="PrivacyPolicy" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Privacy Policy</h4>
				</div>
				<div class="modal-body">
					<p>Some text in the modal.</p>
				</div>
					<div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="Termsandconditions" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Terms and Conditions</h4>
				</div>
				<div class="modal-body">
					<p>This instrument here after referred to as the agreement executed between Applicant and Company. The Company is engaged in the business of Direct Selling through <b>Multi Level Marketing (M.L.M.) </b>and in other business activities as stated in the object clauses of Memorandum and Articles of Association of the Company. The Company authorises Direct Sellers across the country for marketing and sale of its Products and Services. An individual (Indian Citizen only) intended to become Direct Seller can apply for the same in prescribed digital application form of the Company. There is no fee or charge for becoming Direct Seller of the Company. The applicant must be 18 years of age or above. The Company exclusively uses its website to display the details of the products, marketing methods and business monitoring. It also uses verbal publicity to promote its business. For smooth running, simplifying, keeping transparent, prevention of fraudulent practices and betterment of the business of Direct Selling, the Company has framed certain rules and regulations, marketing plan, terms and conditions etc. These terms and conditions are construed in accordance with model guidelines on direct selling issued by the <b>Govt. of India, Ministry of Consumer Affairs, Food & Public Distribution, Department of Consumer Affairs, New Delhi vide F. No. 21/18/2014-IT (Vol.-II) dated 9th Sept, 2016.</b></b></p> <p>WHEREAS the applicant has gone through the Company's official website and read printed documents, brochures including stipulated terms and conditions for becoming Direct Seller.</p> <p>The Company and Direct Seller have clearly understood and agreed to abide by the terms and conditions as laid down herein at the time of agreement.</p> <br> <p><h5><b>NOW THIS AGREEMENT OF DIRECT SELLING WITNESSES AS FOLLOWS:</b></h5></p> <p><b>1.DEFINITIONS:</b></p> <p>The following words used in these presents shall have the meaning as defined hereunder: -</p> <p><b>A.Direct Selling </b></p> <p>Means marketing, distribution and sale of goods/products or providing of services as a part of network of Direct Selling.</p> <p><b>B.Direct Selling Entity</b></p> <p>Means a Company namely M/s. SS MARKET  having its Registered Office and Corporate office at Natesh Complex, 100 Feet Road, Coimbatore, 641 006, running its main business with a brand name of SUPERSHOP which sells or offers to sell goods or services through Direct Seller.</p> <p><b>C.Direct Seller</b></p> <p>Means a person authorised, directly or indirectly, by a Direct Selling Entity through a legally enforceable written contract to undertake direct selling business on principal to principal basis.</p> <p><b>D.Consumer</b></p> <p>Means a person who purchases goods or hires services for personal use/consumption and not for resale or commercial purposes. It shall have the same meaning as provided under the Consumer Protection Act., 1986.</p> <p><b>E.Goods / Products and Services</b></p> <p>Means goods/products as defined in the Sale of Goods Act, 1930. "Services" Means service as defined in Consumer Protection Act, 1986.</p> <p><b>F.Cooling-Off Period</b></p> <p>Means the duration of time counted from the date when the Direct Seller and the Direct Selling Entity enter into an agreement and ending with date on which the contract is to be performed and within which direct seller may repudiate the agreement without being subject to penalty for breach of contract.</p> <p><b>G.Unique ID/Track ID</b></p> <p>Means Unique Identification Number issued by the Company to the Direct Seller as token of acceptance for direct selling of goods/products and services of the Company.</p> <p><b>H.Password</b></p> <p>Means unique password awarded to each Direct Seller to allow him to log on to the website of the Company.</p> <p><b>I.Website</b></p> <p>Means the official website of Company i.e. www.ssmarket.in</p> <p><h5><b>2. THE AUTHORISATION OF DIRECT SELLER AND UNDERSTANDING:</b></h5></p> <p>A.After submitting a digital application form by the applicant, accepting to the terms and conditions of the company, the Company upon scrutiny and verification of the details submitted by applicant in application form may register as "Direct Seller" and authorise him for selling of the goods/products and services of the Company. The Company exclusively reserves the right to accept or reject or decline the application at its discretion without assigning any reason whatsoever.</p> <p>B.The Direct Seller shall enjoy the following privileges: -</p> <p>I.Incentive for effecting sale of goods/products and services of the Company as per marketing plan.</p> <p>II.No territorial restriction to sell the goods/products and services.</p> <p>III.Search and inspect his/her account on website of the Company through I.D. and password awarded by the company.</p> <p>IV.Incentive of the Direct Seller shall be in proportion to the Business Volume of the Direct Seller either by his personal efforts or through team as stipulated in the marketing plan of the Company.</p> <p>V.Direct seller shall be entitled to a <b>Cooling-off Period</b> of 07 days from the date of execution of Agreement between Direct Seller and company without any punishable clause.</p> <p>VI.Direct Seller has the option of <b>Buy back or return of currently marketable goods and services</b> purchased by him/her within 07 days from the date of purchase at Direct Seller's request at reasonable terms. The return of the products must be supported with bill of purchase and such products should not be damaged on any angle. The purchaser should insure that quality and condition of the product should be similar to the quality and condition which was prevailed at the time of purchase. Such return shall be governed by the return policy published on website of the Company.</p> <p>C.An individual, upon appending his/her acceptance by clicking yes on the click box at the bottom of this application form shall be deemed to have accepted the terms and conditions stipulated herein. Upon registration after scrutiny of the details submitted by applicant in application form, he shall become the Direct Seller of the Company. Allotment of password and ID shall be construed as registration as Direct Seller.</p> <p>D.The applicant hereby covenants that as under: -</p> <p>i.That he/she has clearly understood the marketing methods/plan, the compensation plan, its limitations and conditions. He / She agrees that he/she is not relying upon any misrepresentation or fraudulent inducement or assurance that is not set out in terms and conditions or other officially printed or published materials of the Company.</p> <p>ii.Relation between the Company and the Direct Seller shall be governed, in addition to this agreement, by the rules and procedure mentioned in the marketing plan available on website. The Direct Seller further confirms that he/she has read and understood guidelines and terms & conditions carefully and agrees to be bound by them.</p> <p>iii.Direct Seller shall act as freelancer. He shall not commit any misfeasance or malfeasance to create any liability/obligation on the Company of whatsoever nature.</p> <p>iv.Direct Seller shall be responsible for paying all taxes whether direct or indirect including but not limited to income tax, GST and other taxes chargeable to Direct Seller on amount earned hereunder. All legal, statutory, financial and other obligations associated with Direct Seller's business shall be the sole responsibility of Direct Seller.</p> <p>v.It is made and understood in very clear terms that Direct Seller is not an agent, employee, an authorised representative of the Company or its service provider and shall not be entitled to any employee's benefits. He / She is not authorised to receive/accept any amount/payment for and on behalf of the Company. Any payment/amount received by him/her will not be deemed to be received by the Company and the Company shall take necessary action against such Direct Seller.</p> <p>vi.Direct Seller shall keep proper book of account stating the details of the sale of products, price, tax, quantity etc.</p> <p>vii.Direct Seller hereby declare that all the information furnished by him to the Company are true and correct to his/her best of knowledge and nothing is concealed. Company reserves the right to take any action against the Direct Seller in the event of it is discovered that the Direct Seller furnished any wrong/false information to the Company.</p> <p><h5><b>3. GENERAL TERMS: </b></h5></p> <p>i.The Company may appoint any person for collection/distribution services. Direct Seller is required to visit the Company's official website from time to time to get such appointment and avail facilities, make payment, collect valid receipt and products/services from its outlet/permanent retail centre.</p> <p>ii.Direct Seller shall use his/her best efforts to promote the sale of goods/products and services and maximize them. Direct Seller shall also provide reasonable assistance to Company in promotional activities.</p> <p>iii.The Direct Seller will be eligible for remuneration as per business volume of sale of products and services done by him/her subject to the eligibility of norms formulated by the Company from time to time according to marketing plan and not in any other form/manner is payable/given.</p> <p>iv.Track ID has to be quoted by the Direct Seller for all his/her transactions and correspondence with the Company. The Track ID once chosen cannot be altered at any point of time.</p> <p>v.No communication will be entertained without unique ID and password. Direct Seller shall preserve the ID and password properly as it is "must" for logging on website.</p> <p>vi.Incentive to the Direct Seller shall be subject to statutory deductions under income tax and other acts as applicable for the time being in force.</p> <p>vii.The Company reserves right to withheld/block/suspend I.D. of the Direct Seller in the event of the Direct Seller fails to provide any detail as desired by the Company from time to time.</p> <p>viii.Direct Seller undertakes to adhere to policies, procedures, guidelines and rules & regulations formed by the Company.</p> <p>ix.The Direct Seller shall be faithful to the Company and uphold the integrity and decorum of the Company and shall maintain good relations to other Direct Sellers and his/her clients.</p> <p>x.Company reserves the right to modify the terms and conditions, Products, Marketing Plan, Business and other policies at any time without any prior notice. Modification shall be published through the official website of the Company or any other mode as Company may deem fit and proper and such modification/ alteration and amendments made by the Government from time to time, shall be applicable and binding upon the Direct Seller from the date of such modification/alteration.</p> <p>xi.If any Direct Seller loses his contractual capacity due to any reason or in case of death of Direct Seller either his nominee or one of the legal heir with consent in writing of all the legal heirs, may join the Company as Direct Seller in place of incapable or the deceased provided he executes written agreement and undertakes to abide by </p> <p>xii.All rules and regulations and terms & conditions etc. in the same manner as that in case of original Direct Seller. In case of failure to arrival at such consent within six months from the incapability or death of the Direct Seller, the Company shall be at liberty to terminate the unique I.D. and for this period the Company will keep his unique ID in abeyance.</p> <p>xiii.Direct Seller shall be abided by all statutory, central, state and local body laws, rules and regulations and guidelines in operation of Company's business. Direct Seller shall not engage in any deceptive of unlawful trade practices as defined under different statutes.</p> <p>xiv.Direct Seller shall not manipulate the Company's marketing plan, products and services, rate, B.V.6 etc. in any way.</p> <p>xv.Direct Seller shall not send, transmit or otherwise communicate any message to anybody on behalf of the Company without any authority from the Company.</p> <p>xvi.Direct Seller or any other person under him is strictly prohibited to use promotional material, other than the developed and authorised by the company.</p> <p>xvii.Direct Seller shall not use the SUPERSHOP brand, logotype and design anywhere without written permission of the Company and the said permission can be withdrawn at any time by the Company.</p> <p>xviii.Direct Seller shall be self-responsible for all arrangements, expenses and permissions from Central/State Government and local bodies for conducting meetings or seminars.</p> <p>xix.Direct Seller shall bear the cost and expenses of conducting its business in accordance with these terms and conditions. The Company will not entertain any reimbursement on any expense made by the Direct Seller other than sales incentive earned by the Direct Seller as per the marketing plan.</p> <p>xx.Direct Seller shall carry his/her Identity Card issued by the company and not visit the consumer premises without prior appointment/approval. He shall not use I.D. Card apart from Company purposes.</p> <p>xxi.Direct Seller shall not sell any product for a price exceeding Maximum Retail Price (M.R.P.)</p> <p>xxii.Direct Seller shall not make medical claim for the effects arising out by using the products of the Company.</p> <p><h5><b>4. PROHIBITIONS/ RESTRICTIONS:</b></h5></p> <p>i.Direct Seller shall not engage in any activities of Multi Level Marketing of any other Company/Person. If it is found, such direct seller shall be terminated.</p> <p>ii.Direct Seller is prohibited from listening, marketing, advertising, promoting, discussing or selling of any product or the business opportunity on any website or online forum that offers like auction as a mode of selling.</p> <p>iii.The Direct Seller hereby undertakes not to compel or induce or mislead any person with any false statement or promise to purchase products or services from the Company or to become direct seller of the Company.</p> <p><h5><b>5. DUTY AND CONFIDENTIALITY:</b></h5></p> <p>Direct Seller shall keep and maintain secrecy and confidentiality and shall not disclose the secret information to anybody.</p> <p><h5><b>6. SPECIAL CONDITIONS:</b></h5></p> <p>Notwithstanding anything stated or provided herein, the Company shall have all powers and discretion to modify, alter or vary the terms and conditions in any manner or mode as the Company deems fit and proper and shall be communicated through official website. If any Direct Seller does not agree to such modifications/ alterations, he may terminate his agreement within 30 days of such publication by giving a written notice to the Company. Without any objection to such modifications/alterations, if Direct Seller continues his/her business activities, it will be deemed that he/she has accepted all modifications/alterations for future.</p> <p><h5><b>7. TERMINATION: </b></h5></p> <p>Company may terminate this agreement on ground of any reason which shall not be limited to the following: -</p> <p>a.On non-compliance with the provisions of the marketing plan and discipline of the Company.</p> <p>b.For reason of non-performance with the business activities of the Company.</p> <p>c.For any unethical or prejudicial work to the interest of the Company.</p> <p>d.For breach of any term and condition of this agreement and marketing plan.</p> <p>e.In case information given by Direct Seller is found to be misleading/wrong/false.</p> <p>f.If he/she is found to be convicted on any offence punishable under law for the time being in force.</p> <p>g.If he/she is declared bankrupt/insolvent.</p> <p>h.If he/she is not mentally sound or physically fit to handle the business.</p> <p>i.If he/she migrates to other country.</p> <p>j.Where a Direct Seller is found to</p> <p>k. have made no purchase/sale by himself/herself of goods/products and services for a period of two years since the date of joining as Direct Seller or where there is no purchase/sale of goods/products and services for a period of two years since the date of last purchase/sale made, the company shall have the right to terminate the agreement by giving thirty days notice in writing or by electronic means to the direct seller.</p> <p>l.Where Company deems it necessary to terminate the Direct Seller in the interest of other Direct Sellers connected with his/her group/team.</p> <p>The Direct seller may terminate this agreement at any time by giving written notice to the Company.</p> <p><b>A.</b> Termination of a Direct Seller means termination of.</p> <p>i.All rights and entitlements as Direct Seller of Company.</p> <p>ii.Personal information given on website</p> <p>iii.Identification as Direct Seller of Company</p> <p>iv.Right to go at any Company's office and attend Company's meetings/seminars.</p> <p>All the Company's trademarks, trade names, data, photographs, literature, sales aids and all kinds of customer related database and any other information generated shall always remain the property of Company. Within five (5) days after the termination of direct seller, he/she shall return all such items to Company. Direct Seller shall not make or retain copies of any confidential item or information that may have been entrusted to him/her and upon the termination of direct seller, he/she shall cease to use all trademarks and trade names of Company.</p> <p><h5><b>8. RENEWAL / AGREEMENT PERIOD: </b></h5></p> <p>Direct Seller authorisation shall continue till the end of twelve months from the date of acceptance of application by Company. To continue the authorisation as Direct Seller of Company for next one year, the Direct Seller shall have to renew his/her authorisation on or before the last date of expiry of twelve months. In case of failure in submission of renewal application within stipulated period, the authorisation shall be ceased automatically.</p> <p>Renewal application is available on Company's website in personal information. Direct Seller has to apply for renewal through his/her login.</p> <p>Company reserves the right to refuse any renewal request and can revoke agreement if, in Company's opinion, the activities of the Direct Seller are not in the interest of Company or the Direct Seller has failed to comply with the rules, procedures, guidelines, terms & conditions etc. during the twelve proceeding months.</p> <p>The agreement will automatically come to an end in case of non-compliance of renewal formalities.</p> <p><h5><b>9. FORCE MAJEURE: </b></h5></p> <p>The Company shall not be liable for any failure to perform its obligations where such failure has resulted due to acts of nature (including fire, flood, earthquake, storm, hurricane or other natural disaster), war, invasion, act of foreign enemies, hostilities (whether war is declared or not), civil war, rebellion, revolution, insurrection, military or usurped power or confiscation, terrorist activities, nationalization, Government sanction, blockage, embargo, labour dispute, strike, lockout or interruption or failure of electricity, any type of redirection by Government (Central/State), Local Authority etc.</p> <p><h5><b>10.DISPUTE SETTLEMENT AND ARBITRATION:</b></h5></p> <p>1.If any dispute or difference arises out of or in relation to this agreement including any question regarding its existence, validity, termination or terms and conditions, the company and Direct Seller shall endeavour to settle through mutual discussions within 30 days of such dispute arising. In case of dispute or difference is not mutually settled within period, it shall be referred to Grievance Redressal Committee of the Company. If Direct Seller is not satisfied with the decision of Grievance Redressal Committee and dispute or difference is remaining unsolved, the same shall be referred to sole Arbitrator appointed by the Company in accordance with the provisions of Arbitration and conciliation Act, 1996 as amended from time to time. The arbitration proceedings shall be conducted at Coimbatore in the state of Tamilnadu and language shall be English. The decision of Arbitrator will be final and shall have binding effect on the both parties to the agreement.</p> <p>2.The terms and conditions stipulated in the forgoing paragraphs shall be governed in accordance with the law for the time being in force in India. Disputes, either civil or criminal in nature, shall be subject to the exclusive jurisdiction of the court in Coimbatore (T.N.) only.</p> <p><h5><b>11. SMS. ALERTS: </b></h5></p> <p>The Direct Seller agrees to receive the SMS Alert from the company on Mobile No. mentioned/ quoted above and will not object even if they are received despite of DND activated. Direct Seller shall intimate the change in Mobile No. (if any).</p>
				</div>
					<div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
			</div>

		</div>
	</div>
</body>

</html>


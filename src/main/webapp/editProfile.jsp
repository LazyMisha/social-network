<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Profile</title>
    </head>
    <body>
    <center>
    <h2>Update your profile info<h2/>
    <br/><br/>
        <form action="editUser" method="post">
        <label>Your name: <input type="text" name = "firstName" value=""></label>
        <br/>
        <br/>
        <label>Last Name: <input type="text" name="lastName" value=""></label>
        <br/>
        <br/>
        <label>Email: <input type="email" name="email" value=""></label>
        <br/><br/>
        <label>Password: <input type="password" name="password" value=""></label>
        <br/><br/>
        <label>Date of Birth: <select id="birth-day-field" name="birth-day">
        								<option value="01">1</option>
        								<option value="02">2</option>
        								<option value="03">3</option>
        								<option value="04">4</option>
        								<option value="05">5</option>
        								<option value="06">6</option>
        								<option value="07">7</option>
        								<option value="08">8</option>
        								<option value="09">9</option>
        								<option value="10">10</option>
        								<option value="11">11</option>
        								<option value="12">12</option>
        								<option value="13">13</option>
        								<option value="14">14</option>
        								<option value="15">15</option>
        								<option value="16">16</option>
        								<option value="17">17</option>
        								<option value="18">18</option>
        								<option value="19">19</option>
        								<option value="20">20</option>
        								<option value="21">21</option>
        								<option value="22">22</option>
        								<option value="23">23</option>
        								<option value="24">24</option>
        								<option value="25">25</option>
        								<option value="26">26</option>
        								<option value="27">26</option>
        								<option value="28">28</option>
        								<option value="29">29</option>
        								<option value="30">30</option>
        								<option value="31">31</option>
        							</select>
        							<select id="birth-month-field" name="birth-month">
        								<option value="01">01</option>
        								<option value="02">02</option>
        								<option value="03">03</option>
        								<option value="04">04</option>
        								<option value="05">05</option>
        								<option value="06">06</option>
        								<option value="07">07</option>
        								<option value="08">08</option>
        								<option value="09">09</option>
        								<option value="10">10</option>
        								<option value="11">11</option>
        								<option value="12">12</option>
        							</select>
        							<select id="birth-year-field" name="birth-year">
        								<option value="1970">1970</option>
        								<option value="1971">1971</option>
        								<option value="1972">1972</option>
        								<option value="1973">1973</option>
        								<option value="1974">1974</option>
        								<option value="1975">1975</option>
        								<option value="1976">1976</option>
        								<option value="1977">1977</option>
        								<option value="1978">1978</option>
        								<option value="1979">1979</option>
        								<option value="1980">1980</option>
        								<option value="1981">1981</option>
        								<option value="1982">1982</option>
        								<option value="1983">1983</option>
        								<option value="1984">1984</option>
        								<option value="1985">1985</option>
        								<option value="1986">1986</option>
        								<option value="1987">1987</option>
        								<option value="1988">1988</option>
        								<option value="1989">1989</option>
        								<option value="1990">1990</option>
        								<option value="1991">1991</option>
        								<option value="1992">1992</option>
        								<option value="1993">1993</option>
        								<option value="1994">1994</option>
        								<option value="1995">1995</option>
        								<option value="1996">1996</option>
        								<option value="1997">1997</option>
        								<option value="1998">1998</option>
        								<option value="1999">1999</option>
        								<option value="2001">2001</option>
        								<option value="2002">2002</option>
        								<option value="2003">2003</option>
        								<option value="2004">2004</option>
        								<option value="2005">2005</option>
        								<option value="2006">2006</option>
        								<option value="2007">2007</option>
        								<option value="2008">2008</option>
        								<option value="2009">2009</option>
        								<option value="2010">2010</option>
        							</select></label>
        							<br/><br/>
        <label>User info: <input type="text" name="userInfo" value=""></label>
        			<br/><br/>

        <label>Country: <input type="text" name="country" value=""></label>
                                    			<br/><br/>
        <label>City: <input type="text" name="city" value=""></label>

                                    				<br/><br/>
        <input type="submit" name="submit" value="Save">
        <form/>
        <br/><br/>

        ${message}
        <br/><br/>
       <a href="profile.jsp">Your Profile</a><br/>
    <center/>
    <footer class="row">
    				<div class="span12">
    					<p>Social Network fo music 2017</p>
    				</div>
    			</footer>
  </body>
</html>
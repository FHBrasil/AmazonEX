<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>


<div class="testNewsletterPanel container">

    <form id="command" class="right" method="POST" action="/newsletter/newsletter-register">

    <input type="text" name="name" placeholder="Your name"></input><br>
    <input type="email" name="email" placeholder="Your e-mail"></input><br>
    
	<label for="male">Male</label>
  	<input type="radio" name="gender" value="MALE"></input>
  	<label for="female">Female</label>
  	<input type="radio" name="gender" value="FEMALE"></input><br>

    <button>Subscribe</button>

	</form>

</div> 


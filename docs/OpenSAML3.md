# This is a mimimal set to to integrate with remote SAML idp

## IDP side configuration
 -- Set an application in their system (which is for P5)
 -- Setup the user
 -- Configure the login and logout call back Url
 -- (provide certificate to SP)

## SP side configuration
 -- Configure SAML Login & Logout Url which is used to login
 -- Configure certificate(it is from idp) which is used to verify the SAMLResponse

## opensaml Coding Example
### Jars required
	<dependency>
  	  <groupId>org.opensaml</groupId>
 	  <artifactId>opensaml-saml-api</artifactId>
      <version>3.3.1</version>
    </dependency>
    <dependency>
  	  <groupId>org.opensaml</groupId>
 	  <artifactId>opensaml-saml-impl</artifactId>
      <version>3.3.1</version>
      <scope>runtime</scope>
    </dependency>
	
### Init a ParsePool for SAML Response
    InitializationService.initialize();
    
    XMLObjectProviderRegistry xmlObjectProviderRegistry =
        ConfigurationService.get(XMLObjectProviderRegistry.class);
    
    ParserPool parserPool = xmlObjectProviderRegistry.getParserPool();

### SP Process IDP callback - IDP should call this Url with SAMLResponse
	@POST
	@Path("/loginCallback")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ssoLoginCallback(@FormParam(value = "SAMLResponse") String SAMLResponse) {

	  XMLObject samlXMLObject = XMLObjectSupport.unmarshallFromInputStream(parserPool, new ByteArrayInputStream(Base64.getMimeDecoder().decode(samlResponse)));      
	  org.opensaml.saml.saml2.core.Response samlResponseObject =  (org.opensaml.saml.saml2.core.Response)samlXMLObject;
	  
	  Signature signature = samlResponseObject.getAssertions().get(0).getSignature();
	  X509Certificate x509Certificate = signature.getKeyInfo().getX509Datas().get(0).getX509Certificates().get(0);
	  byte[] certificate = Base64.getMimeDecoder().decode(x509Certificate.getValue())
	  
	  // Validate the certificate
	  SignatureValidator.validate(signature, new BasicX509Credential(X509Support.decodeCertificate(certificate)));
	  
	  // verify certificate is the same as the one stored in SP DB
	  ...
	  
	  // Get User
	  String username = samlResponseObject.getAssertions().get(0).getSubject().getNameID().getValue();
	  // verify username exists in SP DB
	  ... 
	  
	  }

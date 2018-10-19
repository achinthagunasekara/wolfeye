# Keys for SSL Communication

We need to create a self signed certificate for the SSL communication. Password I used here is 'test123'.

## How To

```bash
archieg@Archies-MacBook-Pro:certs (master)$ keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass test123 -validity 360 -keysize 2048
What is your first and last name?
  [Unknown]:  Achintha Gunasekara
What is the name of your organizational unit?
  [Unknown]:  IT 
What is the name of your organization?
  [Unknown]:  Home
What is the name of your City or Locality?
  [Unknown]:  Melbourne
What is the name of your State or Province?
  [Unknown]:  VIC
What is the two-letter country code for this unit?
  [Unknown]:  AU
Is CN=Achintha Gunasekara, OU=IT, O=Home, L=Melbourne, ST=VIC, C=AU correct?
  [no]:  yes

Enter key password for <selfsigned>
  (RETURN if same as keystore password):  
archieg@Archies-MacBook-Pro:certs (master)$
```

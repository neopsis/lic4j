**lic4j** is a small library that can help you create and check a license
for your Java software. How to use the library?

##### Create a public/private key pair

```
   KeyPair kp   = null;
   try {
      kp = LicenseUtils.getKeyPair();
   } catch (NoSuchAlgorithmException e) {
       e.printStackTrace();
   } catch (NoSuchProviderException e) {
       e.printStackTrace();
   }

   String PUBLIC_KEY  = ByteHex.convert(kp.getPublic().getEncoded());
   String PRIVATE_KEY = ByteHex.convert(kp.getPrivate().getEncoded());

   System.out.println("PUBLIC : " + PUBLIC_KEY);
   System.out.println("PRIVATE: " + PRIVATE_KEY);
```
Write down both keys. Make private key a part of you licensing utility. 
Make sure the private key is not publicly accessible. 
Distribute the public key as a part of your application (see later). 

##### Create a License Pojo object

Define a new POJO type with licensed features as fields.

```
public class MyLicense extends AbstractLicense {

    @JSONField(name = "HostID")
    private String hostId;

    @JSONField(name = "Option")
    private Boolean option;

    @JSONField(name = "Capacity")
    private Integer caapcity;

   /**
    * Create default constructor withou parameters
    */
    public MyLicense() {}
    
    // setters and getters
    // . . . .
    }
```


##### Create, sign and save the license

Create a new license object, set the features you are granting, sign
the license with your private key write the signed license to a JSON file. 
 

```
  public static String PRIVATE_KEY="308201B83082012C06072A8648CE380401308201....";

  // create the license object
  MyLicense lic = new MyLicense();
  lic.setFeature1("some text");
  lic.setFeature2(true);
  lic.setFeature3(100);
 
  // sign with the private key
  lic.sign(PRIVATE_KEY);
 
  // write down
  LicenseUtils.writeLicense(lic, "license.json")
```

Distribute the file with your software.

##### Verify and check the license

Make the public key a part of your application. In your application, 
read the license and verify the signature with your public key. 
If the signature was successfully verified, you know the license 
has not tampered, and you can check the features in your software. 

```
public static String PUBLIC_KEY = "33082014B0201003082012C06072A8648CE380401...";

try {
   MyLicense lic = LicenseUtils.readLicense("license.json", MyLicense.class);
 
   if (lic.verify(PUBLIC_KEY)) {
       if (  lic.getHostId().equals("some text") 
          && lic.getOption()
          && lic.getCapacity() == 100) {
             System.out.println("License is valid");
       } else {
             // signature OK but features not
             // "some text"|true|100
             System.out.println("License is invalid);
       }
   } else {
      // wrong signature
      System.out.println("License tampered");
   }
} catch (IllegalAccessException e) {
             e.printStackTrace();
} catch (InstantiationException e) {
             e.printStackTrace();
} catch (GeneralSecurityException e) {
             e.printStackTrace();
}
```
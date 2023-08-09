# dfns

A simple repo to demonstrate integration with [dfns](https://www.dfns.co/) using [java](https://dev.java/). 
We will use service accounts, i.e., machines to interact with dfns

## Useful Links
- [Dashboard](https://app.dfns.ninja/): to create assets, add public keys, ...
- [API](https://api.dfns.ninja): for API interaction
- [Dfns docs](https://docs.dfns.co/): official documentation

## Creation of a service account
- [Generate a key pair](https://docs.dfns.co/dfns-docs/advanced-topics/authentication/credentials/generate-a-key-pair) :depending on your set-up, this would be handled by a [HSM](https://en.wikipedia.org/wiki/Hardware_security_module).

I will use [RSA](https://en.wikipedia.org/wiki/RSA_(cryptosystem)) for this part as I tend to use a cryptographic algorithm not linked to the ones used in 
public blockchains (typically [ECDSA](https://en.wikipedia.org/wiki/Elliptic_Curve_Digital_Signature_Algorithm) & [EdDSA](https://en.wikipedia.org/wiki/EdDSA)), but
obviously, either of those would work.

```shell
# Generate RSA Private Key
openssl genrsa -out rsa2048.pem 2048
# Generate the Public Key
openssl pkey -in rsa2048.pem -pubout -out rsa2048.public.pem
```

In the folder [keys](./keys) you will find both the private and public keys.

Next step is to add this public key, associated with a machine id to  [https://app.dfns.ninja/settings/service-accounts/new](https://app.dfns.ninja/settings/service-accounts/new).

Finally, we just need to make sure we can read the PEM and re-create a private key.  
See [CryptoUtils.java](./code/src/main/java/tj/dfns/security/CryptoUtils.java).
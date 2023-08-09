# dfns

A simple repo to demonstrate integration with [dfns](https://www.dfns.co/) using [java](https://dev.java/). 
We will use service accounts, i.e., machines to interact with dfns

## Useful Links
- [Dashboard](https://app.dfns.ninja/): to create assets, add public keys, ...
- [API](https://api.dfns.ninja): for API interaction
- [Dfns docs](https://docs.dfns.co/): official documentation
- [Typescript SDK](https://github.com/dfnsext/typescript-sdk)

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

## List all service accounts: GET /auth/service-accounts

We need to provide [request headers](https://docs.dfns.co/dfns-docs/getting-started/request-headers):
- X-DFNS-APPID: ap-5cjd0-gkpc0-8vbreg28vehsungm (Under [https://app.dfns.ninja/settings/apps](https://app.dfns.ninja/settings/apps))
- X-DFNS-NONCE: a json formatted object with uuid and a time in ISO format


## Creation of an Ethereum Wallet on Goerli: POST ...
We will then use the service account previously created in order to [create a wallet](https://docs.dfns.co/dfns-docs/api-docs/beta-wallets-api-and-nfts/create-wallet).

The request body would be of the form, it is a POST request to api.dfns.ninja:

```json
{
  "network": "EthereumGoerli",
  "name": "tj-goerli-wallet"
}
```

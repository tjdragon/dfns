package tj.dfns.model;

// https://docs.dfns.co/dfns-docs/api-docs/authentication/user-action-signing/completeuseractionsigning
public final class UserActionPayload {
    public final String challengeIdentifier;
    public final FirstFactor firstFactor;

    public static class FirstFactor {
        public final String kind = "Key";
        public final CredentialAssertion credentialAssertion;

        public FirstFactor(CredentialAssertion credentialAssertion) {
            this.credentialAssertion = credentialAssertion;
        }
    }

    public static class CredentialAssertion {
        public final String credId;
        public final String clientData;
        public final String signature;

        public CredentialAssertion(final String credId, final String clientData, final String signature) {
            this.credId = credId;
            this.clientData = clientData;
            this.signature = signature;
        }
    }

    public UserActionPayload(final String challengeIdentifier, final FirstFactor firstFactor) {
        this.challengeIdentifier = challengeIdentifier;
        this.firstFactor = firstFactor;
    }
}

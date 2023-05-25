# (compressed) public address -> Bech32 address

## (DISCLAIMER) 비공식 예제임

## hexPubKey2address (WalletConnect v1)

- Vault에서 받은 public key는 hex형식의 compressed public key임
- [hexPubKey2address()](https://github.com/dosivault/pubkey2addr-java/blob/main/src/main/java/org/example/Main.java#L66)에 넘기면 `link1`로 시작하는 Bech32 address 반환됨
- [JUnit 예제/실행결과](https://github.com/dosivault/pubkey2addr-java/blob/main/src/main/java/org/example/Main.java#L66)

## pubKey2address (WalletConnect v2)

- `byte[]` 형식의 compressed public key를 인자로 받음
- `cosmos_getAccounts()`를 통해 획득한 `pubkey`는 base64 enoding 형식임
- [Unit test의 base64PubKey2address()](pubKey2address)에 base64 decode하여 bech32 address 예시 구현됨

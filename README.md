# (compressed) public address -> Bech32 address

## (DISCLAIMER) 비공식 예제임

## hexPubKey2address
- Vault에서 받은 public key는 hex형식의 compressed public key임
- [hexPubKey2address()](https://github.com/dosivault/pubkey2addr-java/blob/main/src/main/java/org/example/Main.java#L66)에 넘기면 `link1`로 시작하는 Bech32 address 반환됨
- [JUnit 예제/실행결과](https://github.com/dosivault/pubkey2addr-java/blob/main/src/main/java/org/example/Main.java#L66)

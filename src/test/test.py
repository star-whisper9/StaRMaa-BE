import requests
import base64
import json

# 获取公钥
def get_public_key():
    response = requests.get("http://localhost:8081/api/security/getPublicKey")
    response.raise_for_status()
    return response.json()["publicKey"]

# 加密密码
def encrypt_password(password, public_key):
    from Crypto.PublicKey import RSA
    from Crypto.Cipher import PKCS1_OAEP
    from base64 import b64decode, b64encode

    key = RSA.import_key(b64decode(public_key))
    cipher = PKCS1_OAEP.new(key, hashAlgo=SHA256)
    encrypted_password = cipher.encrypt(password.encode())
    return b64encode(encrypted_password).decode()

# 注册用户
def register_user(user_id, password, email, public_key):
    encrypted_password = encrypt_password(password, public_key)
    body = {
        "userId": user_id,
        "password": encrypted_password,
        "email": email
    }
    response = requests.post("http://localhost:8081/api/user/register", json=body)
    response.raise_for_status()
    return response.json()

# 登录用户
def login_user(user_id, password, public_key):
    encrypted_password = encrypt_password(password, public_key)
    body = {
        "userId": user_id,
        "password": encrypted_password
    }
    response = requests.post("http://localhost:8081/api/user/login", json=body)
    response.raise_for_status()
    return response.json()

if __name__ == "__main__":
    public_key = get_public_key()
    user_id = "1"
    password = "123456"
    email = "star@sotis.space"

    # 注册用户
    register_response = register_user(user_id, password, email, public_key)
    print("Register Response:", register_response)

    # 登录用户
    login_response = login_user(user_id, password, public_key)
    print("Login Response:", login_response)
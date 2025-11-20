# gateway-service/app.py
from fastapi import FastAPI
import httpx

SERVER_URL = "http://localhost:8002"

app = FastAPI(title="Gateway Microservice")

@app.get("/gateway/user/{user_id}")
async def gateway_user(user_id: int):
    async with httpx.AsyncClient() as client:
        resp = await client.get(f"{SERVER_URL}/user/{user_id}")
        server_data = resp.json()

    if "error" in server_data:
        return server_data

    # Gateway transforms the fields for Client Microservice use
    transformed = {
        "userId": server_data["id"],
        "fullName": server_data["name"],
        "contact": server_data["email"],   # <-- THIS WILL BREAK IF SERVER CHANGES
    }

    return transformed

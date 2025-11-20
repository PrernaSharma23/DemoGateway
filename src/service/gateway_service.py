from repository.server_client import server_get
from model.gateway_user_model import GatewayUser, GatewayUserList

async def get_transformed_user(user_id: int):
    data = await server_get(f"/user/{user_id}")
    return GatewayUser(
        userId=data["id"],
        fullName=data["name"],
        contact=data["email"]
    )

async def get_transformed_users():
    raw = await server_get("/user/")
    users = [
        GatewayUser(
            userId=u["id"],
            fullName=u["name"],
            contact=u["email"]
        )
        for u in raw
    ]
    return GatewayUserList(users=users)

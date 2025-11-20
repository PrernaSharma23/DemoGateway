from fastapi import APIRouter, HTTPException
from service.gateway_service import get_transformed_user, get_transformed_users
from model.gateway_user_model import GatewayUser, GatewayUserList
from model.error_model import ErrorResponse

router = APIRouter()

@router.get("/user/{user_id}", response_model=GatewayUser, responses={404: {"model": ErrorResponse}})
async def user(user_id: int):
    return await get_transformed_user(user_id)

@router.get("/users", response_model=GatewayUserList)
async def users():
    return await get_transformed_users()

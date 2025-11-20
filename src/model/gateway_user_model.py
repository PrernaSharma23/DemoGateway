from pydantic import BaseModel
from typing import List

class GatewayUser(BaseModel):
    userId: int
    fullName: str
    contact: str

class GatewayUserList(BaseModel):
    users: List[GatewayUser]

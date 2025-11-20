from fastapi import APIRouter
from service.health_service import get_health

router = APIRouter()

@router.get("/health")
async def health():
    """
    Detailed health check for Gateway. Checks Server dependency.
    """
    return await get_health()

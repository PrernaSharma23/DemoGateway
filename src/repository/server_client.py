import httpx
from fastapi import HTTPException

SERVER_URL = "http://localhost:8002"

async def server_get(path: str):
    """
    path must begin with /user or /user/ etc.
    Returns JSON body or raises HTTPException for error conditions.
    """
    url = f"{SERVER_URL}{path}"
    try:
        async with httpx.AsyncClient(timeout=5.0) as client:
            resp = await client.get(url)
    except httpx.RequestError as e:
        raise HTTPException(status_code=503, detail=f"Server unreachable: {e}")

    if resp.status_code == 404:
        raise HTTPException(status_code=404, detail="User not found")
    if resp.status_code != 200:
        raise HTTPException(status_code=resp.status_code, detail="Upstream error")

    return resp.json()

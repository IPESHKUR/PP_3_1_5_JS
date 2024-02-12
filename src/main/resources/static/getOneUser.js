async function getOneUser(id) {
    let url = "/api/users/" + id;
    let response = await fetch(url);
    return await response.json();
}
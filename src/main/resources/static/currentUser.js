'use strict';


function getCurrentUser() {
    fetch("/api/users/one")
        .then(res => res.json())
        .then(user => {
            // const roles = user.roles.map(role => role.role).join(',')
            let role1;
            let ff = 0;

            if (user.roles.length == 1) {
                role1 = "ADMIN" + ", " + "USER"
            } else {
                role1 = user.roles[ff].name.replace("ROLE_", "");
            }

            $('#usernameCurrentUser').append(`<span>${user.username}</span>`)
            $('#roleCurrentUser').append(`<span>${role1}</span>`)
            const u = `$(
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.age}</td>
                        <td>${user.username}</td>
                        <td>${role1}</td>
                    </tr>)`;
            $('#oneUser').append(u)
        })
}

getCurrentUser()
'use strict';
const tbody = $('#AllUsers');
getTableUser();

function getTableUser() {
    tbody.empty();
    fetch("/api/users")
        .then(res => res.json())
        .then(js => {
            console.log(js);
            let ff = 0;
            let role1;
            js.forEach(user => {
                // const roles = user.roles.map(role => role.role).join(',');

                if (user.roles.length == 2) {
                    role1 = "ADMIN" + ", " + "USER"
                } else {
                    role1 = user.roles[ff].name.replace("ROLE_", "");
                }
                const users = $(
                    `<tr>
                        <td class="pt-3" id="userID">${user.id}</td>
                        <td class="pt-3" >${user.name}</td>
                        <td class="pt-3" >${user.surname}</td>
                        <td class="pt-3" >${user.age}</td>
                        <td class="pt-3" >${user.username}</td>
                        <td class="pt-3" >${role1}</td>
                        <td>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#edit" onclick="editModal(${user.id})">
                            Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete" onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>`
                );
                tbody.append(users);
            });
        })
}

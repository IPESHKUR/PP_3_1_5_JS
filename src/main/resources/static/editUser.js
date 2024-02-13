let formEdit = document.forms["formEdit"];
editUser();

async function editModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#edit'));
    await openAndFillInTheModal(formEdit, modal, id);
    formEdit.roles.options[1].selected=true;
    formEdit.password.value;
}

function editUser() {
    formEdit.addEventListener("submit", async (ev) => {
        ev.preventDefault();
        let roles = [];
        for (let i =  0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) {
                roles.push("ROLE_" + form.roles.options[i].text);
            }
        }
        try {
            const response = await fetch("/api/users/" + formEdit.id.value, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: formEdit.id.value,
                    name: formEdit.name.value,
                    surname: formEdit.surname.value,
                    age: formEdit.age.value,
                    username: formEdit.username.value,
                    password: formEdit.password.value,
                    roles: roles
                })
            });

            if (!response.ok) {
                // Обработка ошибки
                const errorText = await response.text();
                alert(`Ошибка при обновлении пользователя: ${errorText}`);
                return;
            }

            // Закрытие модального окна и обновление таблицы
            $('#closeEdit').click();
            getTableUser();
        } catch (error) {
            // Обработка сетевых ошибок
            console.error('Произошла ошибка:', error);
            alert('Произошла ошибка при обновлении пользователя. Пожалуйста, попробуйте еще раз.');
        }
    });
}

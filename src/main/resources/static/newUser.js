'use strict';

let form = document.forms["create"];
form.roles.options[1].selected=true;
console.log(form);

createNewUser()
function createNewUser() {
    form.addEventListener("submit", async (ev) => {
        ev.preventDefault();
        let roles = [];
        for (let i =  0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) roles.push("ROLE_" + form.roles.options[i].text);
        }
        try {
            const response = await fetch("/api/users", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: form.name.value,
                    surname: form.surname.value,
                    age: form.age.value,
                    username: form.username.value,
                    password: form.password.value,
                    roles: roles
                })
            });

            if (!response.ok) {
                // Обработка ошибки
                const errorText = await response.text();
                alert(`Ошибка при создании пользователя: ${errorText}`);
                return;
            }

            // Сброс формы и обновление таблицы
            form.reset();
            $('#home-tab').click();
            getTableUser();
        } catch (error) {
            // Обработка сетевых ошибок
            console.error('Произошла ошибка:', error);
            alert('Произошла ошибка при создании пользователя. Пожалуйста, попробуйте еще раз.');
        }
    });
}





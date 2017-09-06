import $ from 'jquery';
import { Button, Modal } from '@starchart-labs/flightdeck';

let createProjectModal;
let createProjectNameInput;

const functions = {
    loadProjectList() {
        fetch('/projects', {
            headers: new Headers({
                Accept: 'text/html'
            })
        }).then((response) => {
            response.text().then((text) => {
                $('#project-list').replaceWith(text);
            });
        });
    },
    createProject() {
        return new Promise((resolve) => {
            const formData = {
                name: createProjectNameInput.val()
            };
            fetch('/projects', {
                method: 'POST',
                headers: new Headers({
                    'Content-Type': 'application/org.starchartlabs.v1+json'
                }),
                body: JSON.stringify(formData)
            }).then(() => {
                functions.loadProjectList();
                createProjectModal.close();
                resolve();
            });
        });
    }
};

$(() => {
    createProjectNameInput = $('#create-project-name');
    createProjectModal = new Modal('createProjectModal', false, () => createProjectNameInput.val(''));
    new Button('btn-create-project').onClick(() => createProjectModal.open());
    new Button('btn-create-project-modal').onClickAsync(functions.createProject);
    
    functions.loadProjectList();
});

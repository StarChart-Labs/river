import $ from 'jquery';
import { Button, Modal } from '@starchart-labs/flightdeck';

let createProjectModal;
let createProjectNameInput;
const projectDeleteButtons = [];

const functions = {
    loadProjectList() {
        fetch('/projects', {
            headers: new Headers({
                Accept: 'text/html'
            })
        }).then((response) => {
            response.text().then((text) => {
                // reload the projects list
                $('#project-list').replaceWith(text);
                // empty the list of project-delete buttons
                projectDeleteButtons.length = 0;
                // add the new project delete buttons and wire them up
                $('.project-list-item button').each((i, item) => {
                    projectDeleteButtons.push(new Button(item.id)
                        .onClickAsync(() => functions.deleteProject(item.value)));
                });
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
    },
    deleteProject(projectHref) {
        return new Promise((resolve) => {
            fetch(projectHref, {
                method: 'DELETE'
            }).then(() => {
                functions.loadProjectList();
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

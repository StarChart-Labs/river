import $ from 'jquery';
import Modal from './modal';
import Button from './button';

let createProjectModal;

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
            const nameInput = $('#create-project-name');
            const formData = {
                name: nameInput.val()
            };
            fetch('/projects', {
                method: 'POST',
                headers: new Headers({
                    'Content-Type': 'application/org.starchartlabs.v1+json'
                }),
                body: JSON.stringify(formData)
            }).then(() => {
                functions.loadProjectList();
                createProjectModal.hide();
                nameInput.val('');
                resolve();
            });
        });
    }
};

$(() => {
    functions.loadProjectList();
    
    createProjectModal = new Modal($('#createProjectModal'));
    new Button($('.btn-create-project')).callback(() => createProjectModal.show());
    new Button($('.btn-create-project-modal')).async(functions.createProject);
});

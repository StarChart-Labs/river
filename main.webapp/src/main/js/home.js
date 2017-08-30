import $ from 'jquery';
import { initializeModal, showModal, hideModal } from './modal';

const els = new Map();

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
        const formData = {
            name: els.get('inputProjectName').val()
        };
        fetch('/projects', {
            method: 'POST',
            headers: new Headers({
                'Content-Type': 'application/org.starchartlabs.v1+json'
            }),
            body: JSON.stringify(formData)
        }).then(() => {
            functions.loadProjectList();
            els.get('inputProjectName').val('');
            hideModal(els.get('modalCreateProject'));
        });
    }
};

$(() => {
    els.set('btnCreateProject', $('.btn-create-project'));
    els.set('modalCreateProject', $('#createProjectModal'));
    els.set('btnModalCreateProject', $('.btn-create-project-modal'));
    els.set('inputProjectName', $('#create-project-name'));
    
    initializeModal(els.get('modalCreateProject'));
    functions.loadProjectList();
    
    els.get('btnCreateProject').on('click', () => showModal(els.get('modalCreateProject')));
    els.get('btnModalCreateProject').on('click', functions.createProject);
});

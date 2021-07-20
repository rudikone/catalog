function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var departmentAdminApi = Vue.resource('/admin/departments{/id}');
var departmentSimpleApi = Vue.resource('/departments{/id}');

Vue.component('departments-form', {
    props: ['departments', 'departmentAttr'],
    data: function () {
        return {
            name: '',
            id: ''
        }
    },
    watch: {
        departmentAttr: function (newVal) {
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Введите наименование отдела" v-model="name" />' +
        '<input type="button" value="Сохранить" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var department = this.name;

            if (this.id) {
                departmentAdminApi.update({id: this.id}, department).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.departments, data.id);
                        this.departments.splice(index, 1, data);
                        this.name = '';
                        this.id = '';
                    }))
            } else {
                departmentAdminApi.save({}, department).then(result =>
                    result.json().then(data => {
                        this.departments.push(data);
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('departments-row', {
    props: ['department', 'editDepartment', 'departments'],
    template:
        '<div>{{ department.name }}' +
        '<span>' +
        '<input type="button" value="Изменить" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editDepartment(this.department);
        },
        del: function () {
            departmentAdminApi.remove({id: this.department.id}).then(result =>{
                if (result.ok){
                    this.departments.splice(this.departments.indexOf(this.department), 1)
                }
            })
        }
    }
});

Vue.component('departments-list', {
    props: ['departments'],
    data: function () {
        return {
            department: null
        }
    },
    template:
        '<div>' +
        '<departments-form :departments="departments" :departmentAttr="department"/>' +
        '<departments-row v-for="department in departments" :key="department.name" :department="department" ' +
        ':editDepartment="editDepartment" :departments="departments"/>' +
        '</div>',
    created: function () {
        departmentSimpleApi.get().then(result =>
            result.json().then(data =>
                data.forEach(department => this.departments.push(department))
            )
        )
    },
    methods: {
        editDepartment: function (department) {
            this.department = department;

        }
    }
});

var app = new Vue({
    el: '#department',
    template: '<departments-list :departments="departments" />',
    data: {
        departments: []
    }
});
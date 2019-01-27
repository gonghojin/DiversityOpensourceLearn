import React, {Component} from 'react';
import PhoneForm from './components/PhoneForm';
import PhoneInfoList from './components/PhoneInfoList';

class App extends Component {
    id = 2

    state = {
        information: [
            {
                id: 0,
                name: '김민준',
                phone: '010-0000-0000'
            },
            {
                id: 1,
                name: '홍길동',
                phone: '010-0000-0001'
            }
        ],
        keyword: ''
    }

    handleCreate = (data) => {
        const {information} = this.state;
        this.setState({
            information: information.concat({
                id: this.id++,
                ...data
            })
        });
        console.log(data);
    };

    handleRemove = (id) => {
        const {information} = this.state;
        this.setState({
            information: information.filter(info => info.id !== id)
        });
    }

    handleUpdate = (id, data) => {
        const {information} = this.state;
        this.setState({
            information: information.map(
                info => id == info.id
                    ? {...info, ...data} // 새 객체를 만들어서 기존의 값 전달받은 data를 덮어씀
                    : info
            )
        });
    }

    handleChage = (e) => {
        this.setState({
            keyword: e.target.value
        });
    }

    render() {
        const {information, keyword} = this.state;
        const filteredList = information.filter(
            info =>
                info.name.indexOf(keyword) !== -1

        );
        return [
            <div>
                <PhoneForm onCreate={this.handleCreate}/>
                <p>
                    <input placeholder="검색할 이름을 입력하세요.."
                           onChange={this.handleChage}
                           value={keyword}
                    />
                </p>
                <PhoneInfoList
                    data={filteredList}
                    onRemove={this.handleRemove}
                    onUpdate={this.handleUpdate}
                />
            </div>
        ]
    }
}

export default App;

import React, {Component} from 'react';
import PhoneForm from './components/PhoneForm';
import PhoneInfoList from './components/PhoneInfoList';

class App extends Component {
    idx = 2

    state = {
        information: [
            {
                id: 0,
                name : '김민준',
                phone : '010-0000-0000'
            },
            {
                id : 1,
                name : '홍길동',
                phone : '010-0000-0001'
            }
        ]
    }

    handleCreate = (data) => {
        const {information} = this.state;
        this.setState({
            information: information.concat({
                idx : this.idx++,
                ...data
            })
        });
        console.log(data);
    };

    render() {
        const {information} = this.state;
        return [
            <div>
                <PhoneForm onCreate={this.handleCreate}/>
                {JSON.stringify(information)}
                <PhoneInfoList data={information}/>
            </div>
    ]
    }
}

export default App;

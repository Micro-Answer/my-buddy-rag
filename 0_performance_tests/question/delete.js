import http from 'k6/http';
import { check, sleep } from 'k6';

const BASE_URL = 'http://localhost:8080/v1/api/rag';

export let options = {
  stages: [
    { duration: '5m', target: 1000 },
    { duration: '5m', target: 1000 },
    { duration: '5m', target: 0 },
  ],
};

export default function () {
  for (let i = 1; i <= 100000; i++) {
    const questionId = `question_${i}`;
    const userId = `user_${i}`;

    const params = {
      headers: {
        'Content-Type': 'application/json',
      },
    };

    const url = `${BASE_URL}/questions/${questionId}?userId=${userId}`;
    const response = http.del(url, null, params);

    check(response, {
      'status is 204': (r) => r.status === 204,
    });

    sleep(0.1);
  }
}
